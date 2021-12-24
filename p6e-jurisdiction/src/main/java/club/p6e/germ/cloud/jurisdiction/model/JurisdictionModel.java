package club.p6e.germ.cloud.jurisdiction.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class JurisdictionModel implements Serializable {
    private Integer uid;
    private Integer weight;
    private String param;
}
