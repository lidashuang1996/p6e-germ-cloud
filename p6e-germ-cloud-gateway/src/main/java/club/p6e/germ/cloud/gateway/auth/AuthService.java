package club.p6e.germ.cloud.gateway.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 认证服务
 *
 * 自定义认证过滤器
 * 1. 从请求头中读取数据
 * 2. 读取 REDIS 中的数据
 * 3. 验证数据
 * 4. 验证通过放行
 * 5. 注意执行的顺序
 *
 * @author lidashuang
 * @version 1.0
 */
public interface AuthService {

    /** 统一携带用户信息头 */
    String P6E_USER_HEADER = "P6e-User";

    /** 格式化时间对象 */
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 认证异常结果统一返回内容
     * @param exchange ServerWebExchange 对象
     * @return Mono<Void>
     */
    static Mono<Void> exceptionErrorResult(ServerWebExchange exchange) {
        final ServerHttpRequest request = exchange.getRequest();
        final ServerHttpResponse response = exchange.getResponse();
        final String result = "{\"timestamp\":\"" + DATE_TIME_FORMATTER.format(LocalDateTime.now()) + "\",\"path\":\""
                + request.getPath() + "\",\"message\":\"Bad Request\",\"requestId\":\"" + request.getId() + "\",\"code\":400}";
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8))));
    }

    /**
     * 执行认证操作
     * @param exchange ServerWebExchange 对象
     * @return ServerWebExchange 对象
     */
    Mono<ServerWebExchange> execute(ServerWebExchange exchange);

}
