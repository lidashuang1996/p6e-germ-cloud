package club.p6e.germ.message.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MessageLogDb extends MessageBaseDb implements Serializable {
    private Integer id;
    private Integer pid;
    private Integer tid;
    private Integer status;
    private String mark;
    private String source;
    private String content;
    private String date;
    private List<MessageLogDb> items;
}
