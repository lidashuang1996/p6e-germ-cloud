package club.p6e.germ.message.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MessagePlatformDb extends MessageBaseDb implements Serializable {
    private Integer id;
    private Integer weight;
    private Integer status;
    private String limit;
    private String config;
    private String name;
    private String actuator;
    private String describe;
    private String createDate;
    private String updateDate;
    private String operate;
    /** 扩展 */
    private Integer groupId;
}
