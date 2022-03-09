package club.p6e.germ.cloud.gateway.auth;

import club.p6e.germ.cloud.common.utils.P6eJsonUtil;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 对认证缓存服务的实现
 * 认证缓存服务采用的 REDIS 的方法实现
 * @author lidashuang
 * @version 1.0
 */
@Component
public class AuthRedisCache implements AuthCache {

    @Resource
    private ReactiveStringRedisTemplate reactiveStringRedisTemplate;

    @Override
    public Mono<String> getUser(String uid) {
        return reactiveStringRedisTemplate.opsForValue().get(USER_PREFIX + uid).flatMap(content -> {
            if (content == null) {
                return Mono.empty();
            } else {
                return Mono.just(content);
            }
        });
    }

    @Override
    public Mono<Token> getAccessToken(String token) {
        return reactiveStringRedisTemplate.opsForValue().get(ACCESS_TOKEN_PREFIX + token).flatMap(content -> {
            if (content == null) {
                return Mono.empty();
            } else {
                return Mono.just(P6eJsonUtil.fromJson(content, Token.class));
            }
        });
    }
}
