package club.p6e.germ.jurisdiction.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
public class JurisdictionBaseDb implements Serializable {
    private String startDateTime;
    private String endDateTime;
    private Integer page;
    private Integer size;

    private Integer offset;
}
