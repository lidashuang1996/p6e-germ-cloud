package club.p6e.germ.cloud.gateway.auth;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 认证过滤器
 * @author lidashuang
 * @version 1.0
 */
@Component
public class P6eAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    /** 顺序（越小越先被执行） */
    private static final int ORDER = -1000;

    @Resource
    private AuthService service;

    @Override
    public GatewayFilter apply(Object config) {
        return new CustomGatewayFilter(service);
    }

    /**
     * 自定义网关过滤器
     */
    private static class CustomGatewayFilter implements GatewayFilter, Ordered {

        /** 认证服务 */
        private final AuthService service;

        /**
         * 构造方法
         * 通过构造方法初始化认证服务
         * @param service 认证服务
         */
        public CustomGatewayFilter(AuthService service) {
            this.service = service;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            return service.execute(exchange).flatMap(chain::filter).switchIfEmpty(AuthService.exceptionErrorResult(exchange));
        }

        @Override
        public int getOrder() {
            return ORDER;
        }
    }
}
