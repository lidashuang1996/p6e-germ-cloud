package club.p6e.germ.cloud.auth.domain.keyvalue;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Getter
public class MarkKeyValue implements Serializable {

    /** 记号内容 */
    private final String content;

    public MarkKeyValue(String content) {
        this.content = content;
    }
}
