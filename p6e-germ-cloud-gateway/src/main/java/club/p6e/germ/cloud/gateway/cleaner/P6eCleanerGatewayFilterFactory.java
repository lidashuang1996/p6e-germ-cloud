package club.p6e.germ.cloud.gateway.cleaner;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 清除过滤器网关
 * @author lidashuang
 * @version 1.0
 */
@Component
public class P6eCleanerGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    /** 顺序（越小越先被执行）*/
    private static final int ORDER = -1800;

    @Override
    public GatewayFilter apply(Object config) {
        return new CustomGatewayFilter();
    }

    /**
     * 自定义头部过滤器
     */
    private static class CustomGatewayFilter implements GatewayFilter, Ordered {

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            System.out.println("111111111111111111");
            final ServerHttpRequest request = exchange.getRequest();
            // 删除关键的头
            // 这个请求是内部自己用来调用的
            // 禁止请求发送携带这个请求头到下游服务
            return chain.filter(exchange.mutate().request(request.mutate()
                            // 重写 P6e-User 为空，来达到删除的目的
                            .header("P6e-User", "").build()).build());
        }

        @Override
        public int getOrder() {
            return ORDER;
        }
    }
}
