package club.p6e.germ.cloud.auth.domain.keyvalue;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Getter
public class TokenKeyValue implements Serializable {
    /** 令牌 TOKEN */
    private final String accessToken;
    /** 刷新 TOKEN */
    private final String refreshToken;

    public TokenKeyValue(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
