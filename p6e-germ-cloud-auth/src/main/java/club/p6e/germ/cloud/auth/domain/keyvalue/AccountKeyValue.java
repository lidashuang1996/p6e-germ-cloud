package club.p6e.germ.cloud.auth.domain.keyvalue;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Getter
public class AccountKeyValue implements Serializable {

    /** 类型 */
    private final String type;
    /** 账号 */
    private final String content;

    public AccountKeyValue(String content, String type) {
        this.type = type;
        this.content = content;
    }
}
