package club.p6e.germ.message.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
public class MessageBaseDb implements Serializable {
    private String search;
    private String startDateTime;
    private String endDateTime;
    private Integer page;
    private Integer size;

    private Integer offset;
}
