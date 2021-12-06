package club.p6e.germ.cloud.auth.domain.keyvalue;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Getter
public class CodeKeyValue implements Serializable {

    /** CODE 内容 */
    private final String content;

    public CodeKeyValue(String content) {
        this.content = content;
    }

}
