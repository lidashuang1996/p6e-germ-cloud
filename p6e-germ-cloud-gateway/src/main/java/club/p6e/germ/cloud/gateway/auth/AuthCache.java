package club.p6e.germ.cloud.gateway.auth;

import lombok.Data;
import reactor.core.publisher.Mono;

/**
 * 认证缓存服务
 * @author lidashuang
 * @version 1.0
 */
public interface AuthCache {

    /** 用户缓存前缀 */
    String USER_PREFIX = "P6E:AUTH:USER:";

    /** TOKEN 缓存前缀 */
    String ACCESS_TOKEN_PREFIX = "P6E:AUTH:ACCESS_TOKEN:";

    /**
     * 获取用户的信息
     * @param uid 用户的 ID
     * @return User 对象
     */
    Mono<String> getUser(String uid);

    /**
     * 获取 ACCESS TOKEN 的信息
     * @param accessToken ACCESS TOKEN 数据
     * @return Token 对象
     */
    Mono<Token> getAccessToken(String accessToken);

    /**
     * Token 对象
     */
    @Data
    class Token {
        private String accessToken;
        private String refreshToken;
        private Long expiration;
        private String type;
        private String uid;
    }

}
