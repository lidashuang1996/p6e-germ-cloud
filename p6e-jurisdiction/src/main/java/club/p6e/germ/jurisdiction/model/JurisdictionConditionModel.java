package club.p6e.germ.jurisdiction.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class JurisdictionConditionModel implements Serializable {
    private Integer uid;
    private String type;
    private String rule;
    private String value;
}
