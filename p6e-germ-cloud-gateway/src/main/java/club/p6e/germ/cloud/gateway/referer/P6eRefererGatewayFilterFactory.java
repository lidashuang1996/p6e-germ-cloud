package club.p6e.germ.cloud.gateway.referer;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Referer 过滤器
 * @author lidashuang
 * @version 1.0
 */
@Component
public class P6eRefererGatewayFilterFactory extends AbstractGatewayFilterFactory<P6eRefererGatewayFilterFactory.Config> {

    /** 顺序（越小越先被执行） */
    private static final int ORDER = -1500;

    /**
     * 构造方法初始化
     */
    public P6eRefererGatewayFilterFactory() {
        super(P6eRefererGatewayFilterFactory.Config.class);
    }

    @Resource
    private RefererService service;

    @Override
    public GatewayFilter apply(P6eRefererGatewayFilterFactory.Config config) {
        return new CustomGatewayFilter(service, config.getWhiteList());
    }

    /**
     * 自定义网关过滤器
     */
    private static class CustomGatewayFilter implements GatewayFilter, Ordered {

        /** Referer 服务 */
        private final RefererService service;
        /** 白名单 */
        private final List<String> whiteList;

        /**
         * 构造方法
         * @param service Referer 服务
         * @param whiteList 白名单列表
         */
        public CustomGatewayFilter(RefererService service, List<String> whiteList) {
            this.service = service;
            this.whiteList = whiteList;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            return service
                    .execute(exchange, whiteList)
                    .flatMap(chain::filter)
                    .switchIfEmpty(RefererService.exceptionErrorResult(exchange));
        }

        @Override
        public int getOrder() {
            return ORDER;
        }
    }

    @Data
    public static class Config {
        private List<String> whiteList = new ArrayList<>();
    }
}
