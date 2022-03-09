package club.p6e.germ.cloud.gateway.auth;

import club.p6e.germ.cloud.common.utils.P6eJsonUtil;
import club.p6e.germ.cloud.gateway.P6eGermCloudGatewayApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 认证服务的默认的实现
 * @author lidashuang
 * @version 1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    /** BEARER 长度 */
    private static final int BEARER_LENGTH = 7;
    /** BEARER 内容 */
    private static final String BEARER_CONTENT = "Bearer ";

    /**
     * 注入缓存服务
     */
    @Resource
    private AuthCache cache;

    @Override
    public Mono<ServerWebExchange> execute(ServerWebExchange exchange) {
        final ServerHttpRequest request = exchange.getRequest();
        System.out.println("ggggggggggggggggggggggg");
        System.out.println("----------------");
        request.getHeaders().keySet().forEach(System.out::println);
        System.out.println("----------------");
        final List<String> authorizations = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (authorizations != null && authorizations.size() > 0) {
            System.out.println(authorizations);
            return execute(authorizations.get(0)).flatMap(s ->
                    s == null ? Mono.empty() : Mono.just(exchange.mutate().request(
                            exchange.getRequest().mutate().header(P6E_USER_HEADER, s).build()).build()));
        }
        return Mono.empty();
    }
    /**
     * 通过认证查询用户信息
     * @param authorization 认证信息
     * @return Mono<String> 用户信息
     */
    private Mono<String> execute(String authorization) {
        System.out.println(":xxxxxxxxxxxxxxxxxxxx " + authorization);
        System.out.println(authorization.startsWith(BEARER_CONTENT));
        if (authorization.startsWith(BEARER_CONTENT)) {
            System.out.println("authorization  " + authorization);
            return Mono.just(authorization).flatMap(o -> {
                final String accessToken = authorization.substring(BEARER_LENGTH);
                System.out.println("accessToken   " + accessToken);
                if (P6eGermCloudGatewayApplication.IS_DEBUG) {
                    final AuthModel authModel = new AuthModel()
                            .setId(1)
                            .setState(1)
                            .setRole(1)
                            .setName("test_name")
                            .setNickname("test_nickname")
                            .setAvatar("test_avatar")
                            .setDescribe("test_describe")
                            .setEmail("test_email@qq.com")
                            .setPhone("12345678910")
                            .setJurisdictions(Arrays.asList("1", "2"));
                    System.out.println("debug user info " + P6eJsonUtil.toJson(authModel));
                    return Mono.just(P6eJsonUtil.toJson(authModel));
                } else {
                    return cache.getAccessToken(accessToken).flatMap(token -> cache.getUser(token.getUid()));
                }
            });
        } else {
            return Mono.empty();
        }
    }
}
