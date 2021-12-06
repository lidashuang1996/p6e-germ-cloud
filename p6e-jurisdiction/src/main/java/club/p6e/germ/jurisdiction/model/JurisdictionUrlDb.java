package club.p6e.germ.jurisdiction.model;

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
public class JurisdictionUrlDb extends JurisdictionBaseDb implements Serializable {
    private Integer id;
    private String url;
    private String baseUrl;
    private String method;
    private String config;
    private String name;
    private String describe;
    private String createDate;
    private String updateDate;
    private String operate;
}
