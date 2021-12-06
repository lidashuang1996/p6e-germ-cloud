package club.p6e.germ.cloud.gateway.jurisdiction;

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
 * @author lidashuang
 * @version 1.0
 */
@Component
public class JurisdictionGatewayFilterFactory extends AbstractGatewayFilterFactory<JurisdictionGatewayFilterFactory.Config> {

    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(JurisdictionGatewayFilterFactory.class);

    /** 权限服务 */
    private final Map<String, JurisdictionService> serviceMap;

    @Autowired
    public JurisdictionGatewayFilterFactory(Map<String, JurisdictionService> serviceMap) {
        super(JurisdictionGatewayFilterFactory.Config.class);
        // 非空判断
        Objects.requireNonNull(serviceMap);
        this.serviceMap = serviceMap;
        LOGGER.info("------------------------------------");
        LOGGER.info("Injected jurisdiction services ==>");
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

        /** 配置文件 */
        private final Config config;
        /** 权限服务 */
        private final Map<String, JurisdictionService> serviceMap;

        public CustomGatewayFilter(Config config, Map<String, JurisdictionService> serviceMap) {
            this.config = config;
            this.serviceMap = serviceMap;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            final JurisdictionService service = serviceMap.get(config.getName());
            if (service == null) {
                LOGGER.warn("No corresponding jurisdiction service was found.");
                return JurisdictionService.exceptionResult(exchange);
            } else {
                LOGGER.debug("Jurisdiction service performed ==> " + service.getClass() + " [ " + service + " ]");
                return service.execute(exchange, chain);
            }
        }

        @Override
        public int getOrder() {
            return -10;
        }
    }

    public static class Config {
        private String name;

        public Config() {
            this.name = "JurisdictionServiceDefaultImpl";
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
