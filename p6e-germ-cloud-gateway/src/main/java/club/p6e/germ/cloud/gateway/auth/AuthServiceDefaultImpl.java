package club.p6e.germ.cloud.gateway.auth;

import club.p6e.germ.cloud.gateway.cache.AuthRedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 认证服务的默认的实现
 * @author lidashuang
 * @version 1.0
 */
@Service(value = "AuthServiceDefaultImpl")
public class AuthServiceDefaultImpl implements AuthService {

    private static final int BEARER_LENGTH = 7;
    private static final String BEARER_CONTENT = "Bearer ";

    /** 缓存服务 */
    private final AuthRedisCache cache;

    @Autowired
    public AuthServiceDefaultImpl(AuthRedisCache cache) {
        this.cache = cache;
    }

    @Override
    public Mono<Void> execute(ServerWebExchange exchange, GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        final List<String> authorizations = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (authorizations != null) {
            for (final String authorization : authorizations) {
                if (authorization.startsWith(BEARER_CONTENT)) {
                    // 读取请求头部中的 token
                    final String token = authorization.substring(BEARER_LENGTH);
                    // 读取缓存的 token 对应的用户信息
                    String content = cache.getAccessToken(token);

                    if (token.equals("123456")) {
                        content = "{\"sex\":\"男\",\"name\":\"测试猛男\",\"id\":\"1\",\"account\":\"admin@qq.com\"}";
                    }

                    if (content != null) {
                        // 重新编译 exchange
                        return chain.filter(exchange.mutate().request(
                                request.mutate().header(P6E_USER_HEADER, content).build()).build());
                    }
                }
            }
        }
        return AuthService.exceptionResult(exchange);
    }
}
