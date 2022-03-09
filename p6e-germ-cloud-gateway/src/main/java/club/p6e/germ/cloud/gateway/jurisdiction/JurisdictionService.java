//package club.p6e.germ.cloud.gateway.jurisdiction;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.StandardCharsets;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
///**
// * 权限服务
// * @author lidashuang
// * @version 1.0
// */
//public interface JurisdictionService {
//
//    /** 格式化时间对象 */
//    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//    /** 统一携带用户信息头 */
//    String HKSI_USER_HEADER = "HK-User";
//
//    /**
//     * 异常结果统一返回内容
//     * @param exchange ServerWebExchange 对象
//     * @return Mono<Void>
//     */
//    static Mono<Void> exceptionErrorResult(ServerWebExchange exchange) {
//        final ServerHttpRequest request = exchange.getRequest();
//        final ServerHttpResponse response = exchange.getResponse();
//        final String result = "{\"timestamp\":\"" + DATE_TIME_FORMATTER.format(LocalDateTime.now()) + "\",\"path\":\""
//                + request.getPath() + "\",\"message\":\"Unauthorized\",\"requestId\":\"" + request.getId() + "\",\"code\":401}";
//        response.setStatusCode(HttpStatus.UNAUTHORIZED);
//        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
//        return response.writeWith(Mono.just(
//                response.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8))
//        ));
//    }
//
//    /**
//     * 执行认证操作
//     * @param exchange ServerWebExchange 对象
//     * @return Mono<Void>
//     */
//    Mono<ServerWebExchange> execute(ServerWebExchange exchange);
//}
