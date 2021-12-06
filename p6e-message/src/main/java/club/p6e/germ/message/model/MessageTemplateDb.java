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
public class MessageTemplateDb extends MessageBaseDb implements Serializable {
    private Integer id;
    private String type;
    private String title;
    private String name;
    private String parser;
    private String content;
    private String describe;
    private String createDate;
    private String updateDate;
    private String operate;
    private Integer isDelete;
}
