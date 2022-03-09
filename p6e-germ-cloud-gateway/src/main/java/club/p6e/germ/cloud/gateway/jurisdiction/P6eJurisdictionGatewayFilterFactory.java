//package club.p6e.germ.cloud.gateway.jurisdiction;
//
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import javax.annotation.Resource;
//
///**
// * 权限过滤器网关
// * @author lidashuang
// * @version 1.0
// */
//@Component
//public class P6eJurisdictionGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
//
//    /** 顺序 */
//    private static final int ORDER = -900;
//
//    @Resource
//    private JurisdictionService service;
//
//    @Override
//    public GatewayFilter apply(Object config) {
//        return new CustomGatewayFilter(service);
//    }
//
//    public static class CustomGatewayFilter implements GatewayFilter, Ordered {
//
//        /** 权限服务 */
//        private final JurisdictionService service;
//
//        /**
//         * 构造方法
//         * 通过构造方法初始化权限服务
//         * @param service 权限服务
//         */
//        public CustomGatewayFilter(JurisdictionService service) {
//            this.service = service;
//        }
//
//        @Override
//        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//            System.out.println("999999999999999999999999999");
//            return service.execute(exchange).flatMap(chain::filter).switchIfEmpty(JurisdictionService.exceptionErrorResult(exchange));
//        }
//
//        @Override
//        public int getOrder() {
//            return ORDER;
//        }
//    }
//
//}
