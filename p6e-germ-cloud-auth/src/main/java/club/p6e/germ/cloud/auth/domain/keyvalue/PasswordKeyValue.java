package club.p6e.germ.cloud.auth.domain.keyvalue;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Getter
public class PasswordKeyValue implements Serializable {

    /** 密码内容 */
    private final String content;

    public PasswordKeyValue(String content) {
        this.content = content;
    }
}
