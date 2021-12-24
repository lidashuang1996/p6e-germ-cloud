package club.p6e.germ.cloud.jurisdiction.model;

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Entity(name = "p6e_jurisdiction_url")
@Accessors(chain = true)
public class JurisdictionUrlModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;
    private String baseUrl;
    private String method;
    private String config;
    private String name;
    private String describe;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String operate;
    private Integer isDelete;


}
