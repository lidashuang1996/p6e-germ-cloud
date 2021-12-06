package club.p6e.germ.message.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MessageGroupDb extends MessageBaseDb implements Serializable {
    private Integer id;
    private String type;
    private Integer status;
    private String limit;
    private String route;
    private String name;
    private String describe;
    private String createDate;
    private String updateDate;
    private String operate;
    private List<MessagePlatformDb> platforms = new ArrayList<>();
}
