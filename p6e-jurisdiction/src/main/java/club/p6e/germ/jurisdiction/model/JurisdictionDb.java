package club.p6e.germ.jurisdiction.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class JurisdictionDb implements Serializable {
    private Integer userId;
    private Integer roleId;
    private Integer roleWeight;
    private String roleName;
    private String roleDescribe;
    private String roleParam;
    private String urlId;
    private String urlUrl;
    private String urlBaseUrl;
    private String urlMethod;
    private String urlConfig;
    private String urlName;
    private String urlDescribe;
}
