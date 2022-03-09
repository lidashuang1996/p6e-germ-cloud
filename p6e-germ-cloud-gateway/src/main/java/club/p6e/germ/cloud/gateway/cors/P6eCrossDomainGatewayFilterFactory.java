package club.p6e.germ.cloud.gateway.cors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * Cross Domain 过滤器
 * @author lidashuang
 * @version 1.0
 */
@Component
public class P6eCrossDomainGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    /** 顺序 */
    private static final int ORDER = -1400;

    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(P6eCrossDomainGatewayFilterFactory.class);

    @Override
    public GatewayFilter apply(Object config) {
        return new CustomGatewayFilter();
    }

    /**
     * 自定义网关过滤器
     */
    private static class CustomGatewayFilter implements GatewayFilter, Ordered {

        /** 跨域头名称 */
        private static final String CROSS_DOMAIN_HEADER_NAME = "P6e-Cross-Domain";

        /** 跨域头内容 */
        private static final String CROSS_DOMAIN_HEADER_CONTENT = "existence";

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            System.out.println("222222222222222222222222");
            final ServerHttpRequest request = exchange.getRequest();
            final ServerHttpResponse response = exchange.getResponse();
            LOGGER.debug("Cross Domain ==> " + request.getPath() + " [ " + request.getMethodValue() + " ].");
            final List<String> content = request.getHeaders().get(CROSS_DOMAIN_HEADER_NAME);
            if (content != null && content.size() > 0) {
                // 存在跨域的处理标记，无需处理
                return chain.filter(exchange);
            } else {
                // 不存在跨域的处理标记
                // 设置 Access-Control-Allow-Origin 内容为请求头 origin 的内容
                // 如果不存在 origin 的请求头，那么设置为 *
                String origin = request.getHeaders().getOrigin();
                if (origin == null) {
                    origin = "*";
                }
                response.getHeaders().setAccessControlMaxAge(3600);
                response.getHeaders().setAccessControlAllowOrigin(origin);
                response.getHeaders().setAccessControlAllowMethods(
                        Arrays.asList(
                                HttpMethod.PUT,
                                HttpMethod.GET,
                                HttpMethod.POST,
                                HttpMethod.DELETE,
                                HttpMethod.OPTIONS
                        )
                );
                response.getHeaders().setAccessControlAllowHeaders(
                        Arrays.asList(
                                "Authorization", "Content-Type",
                                "Depth", "User-Agent", "X-File-Size",
                                "X-Requested-With", "X-Requested-By", "If-Modified-Since",
                                "X-File-Name", "X-File-Type", "Cache-Control", "Origin", "Client"
                        )
                );
                return chain.filter(exchange.mutate()
                        .request(request.mutate().header(CROSS_DOMAIN_HEADER_NAME, CROSS_DOMAIN_HEADER_CONTENT).build())
                        .response(response).build());
            }
        }

        @Override
        public int getOrder() {
            return ORDER;
        }
    }
}
