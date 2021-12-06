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
public class MessageGroupRelationPlatformDb extends MessageBaseDb implements Serializable {
    private Integer gid;
    private Integer pid;
}
