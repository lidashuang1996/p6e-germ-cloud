package club.p6e.germ.cloud.auth.domain.keyvalue;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
public enum OtherStateTypeKeyValue implements Serializable {

    /** QQ 类型 */
    QQ("QQ"),
    /** WECHAT 类型 */
    WECHAT("WECHAT");

    private final String name;

    OtherStateTypeKeyValue(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
