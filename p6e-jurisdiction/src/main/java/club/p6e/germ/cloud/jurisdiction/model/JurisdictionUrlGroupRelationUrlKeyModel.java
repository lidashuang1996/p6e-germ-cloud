package club.p6e.germ.cloud.jurisdiction.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class JurisdictionUrlGroupRelationUrlKeyModel implements Serializable {
    private Integer uid;
    private Integer gid;
}
