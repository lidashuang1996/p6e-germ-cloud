package club.p6e.germ.cloud.console.infrastructure.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    public static final String ID = "id";
    public static final String URL = "url";
    public static final String BASE_URL = "base_url";
    public static final String METHOD = "method";
    public static final String CONFIG = "config";
    public static final String NAME = "name";
    public static final String DESCRIBE = "describe";
    public static final String CREATE_DATE = "create_date";
    public static final String UPDATE_DATE = "update_date";
    public static final String OPERATE = "operate";
    public static final String IS_DELETE = "isDelete";

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
