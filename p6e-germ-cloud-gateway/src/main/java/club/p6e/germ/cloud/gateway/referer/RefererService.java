package club.p6e.germ.cloud.gateway.referer;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Referer 服务
 * @author lidashuang
 * @version 1.0
 */
public interface RefererService {

    /** 格式化时间对象 */
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 认证异常结果统一返回内容
     * @param exchange ServerWebExchange 对象
     * @return Mono<Void>
     */
    static @NonNull Mono<Void> exceptionErrorResult(@NonNull ServerWebExchange exchange) {
        final ServerHttpRequest request = exchange.getRequest();
        final ServerHttpResponse response = exchange.getResponse();
        final String result = "{\"timestamp\":\"" + DATE_TIME_FORMATTER.format(LocalDateTime.now()) + "\",\"path\":\""
                + request.getPath() + "\",\"message\":\"No Content\",\"requestId\":\"" + request.getId() + "\",\"code\":204}";
        response.setStatusCode(HttpStatus.NO_CONTENT);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8))));
    }

    /**
     * 执行认证操作
     * @param exchange ServerWebExchange 对象
     * @return ServerWebExchange 对象
     */
    @NonNull Mono<ServerWebExchange> execute(@NonNull ServerWebExchange exchange, @NonNull List<String> whiteList);

}
