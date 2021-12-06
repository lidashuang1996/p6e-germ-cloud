package club.p6e.germ.cloud.auth.domain.module.oauth2;

import club.p6e.germ.cloud.auth.domain.keyvalue.ClientKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.TokenKeyValue;

/**
 * @author lidashuang
 * @version 1.0
 */
public class Oauth2TokenEntity {
    public static final long EXPIRES_IN = 3600L;
    public static final String TOKEN_TYPE = "Bearer";


    private TokenKeyValue token;

    public static Oauth2TokenEntity generate(ClientKeyValue user) {
        return new Oauth2TokenEntity(new TokenKeyValue("1", "2"));
    }

    public Oauth2TokenEntity(TokenKeyValue token) {
        this.token = token;
    }

    public TokenKeyValue getData() {
        return token;
    }
}
