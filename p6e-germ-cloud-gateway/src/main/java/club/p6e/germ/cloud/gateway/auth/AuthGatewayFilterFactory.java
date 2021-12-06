package club.p6e.germ.cloud.gateway.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

/**
 * 认证过滤器
 * @author lidashuang
 * @version 1.0
 */
@Component
public class AuthGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthGatewayFilterFactory.Config> {

    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthGatewayFilterFactory.class);

    /**
     * 注入的认证服务
     * 注入的认证服务可以多个，采用 key-value 的形式保存
     * 最后通过配置文件的名称进行指定需要执行认证服务
     */
    private final Map<String, AuthService> serviceMap;

    @Autowired
    public AuthGatewayFilterFactory(Map<String, AuthService> serviceMap) {
        super(Config.class);
        // 非空判断
        Objects.requireNonNull(serviceMap);
        // 执行注入
        this.serviceMap = serviceMap;
        LOGGER.info("------------------------------------");
        LOGGER.info("Injected authentication services ==>");
        for (final String key : this.serviceMap.keySet()) {
            LOGGER.info("Injected --> " + key + " >> " + this.serviceMap.get(key).getClass());
        }
        LOGGER.info("------------------------------------");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new CustomGatewayFilter(config, serviceMap);
    }

    public static class CustomGatewayFilter implements GatewayFilter, Ordered {

        private final Config config;
        private final Map<String, AuthService> serviceMap;

        public CustomGatewayFilter(Config config, Map<String, AuthService> serviceMap) {
            this.config = config;
            this.serviceMap = serviceMap;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            final AuthService service = serviceMap.get(config.getName());
            if (service == null) {
                // 没有查询到服务，直接认证异常
                LOGGER.warn("No corresponding authentication service was found.");
                return AuthService.exceptionResult(exchange);
            } else {
                LOGGER.debug("Authentication service performed ==> " + service.getClass() + " [ " + service + " ]");
                return service.execute(exchange, chain);
            }
        }

        @Override
        public int getOrder() {
            return -100;
        }
    }

    public static class Config {
        private String name;

        public Config() {
            this.name = "AuthServiceDefaultImpl";
        }

        public Config(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Config setName(String name) {
            this.name = name;
            return this;
        }
    }
}
