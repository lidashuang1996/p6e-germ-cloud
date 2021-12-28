package club.p6e.germ.cloud.console.infrastructure.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class JurisdictionUserRelationUrlGroupKeyModel implements Serializable {
    private Integer uid;
    private Integer gid;
}
