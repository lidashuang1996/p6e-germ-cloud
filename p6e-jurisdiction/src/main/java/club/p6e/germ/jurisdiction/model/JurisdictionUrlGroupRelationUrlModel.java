package club.p6e.germ.jurisdiction.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@IdClass(JurisdictionUrlGroupRelationUrlKeyModel.class)
@Entity(name = "p6e_jurisdiction_url_group_relation_url")
public class JurisdictionUrlGroupRelationUrlModel implements Serializable {
    @Id
    private Integer uid;
    @Id
    private Integer gid;
    private String param;

    @ManyToOne(targetEntity = JurisdictionUrlModel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "uid", referencedColumnName = "id", insertable = false, updatable = false)
    private JurisdictionUrlModel url;
}
