package club.p6e.germ.cloud.console.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JurisdictionUrlGroupRelationUrlKeyModel implements Serializable {
    private Integer gid;
    private Integer uid;
}
