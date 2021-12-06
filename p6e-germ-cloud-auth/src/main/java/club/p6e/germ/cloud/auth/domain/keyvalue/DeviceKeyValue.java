package club.p6e.germ.cloud.auth.domain.keyvalue;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Getter
public class DeviceKeyValue implements Serializable {

    /** 设备内容 */
    private final String content;

    public DeviceKeyValue(String content) {
        this.content = content;
    }

}
