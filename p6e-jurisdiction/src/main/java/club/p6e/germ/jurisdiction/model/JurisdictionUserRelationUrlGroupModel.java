package club.p6e.germ.jurisdiction.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@IdClass(JurisdictionUserRelationUrlGroupKeyModel.class)
@Entity(name = "p6e_jurisdiction_user_relation_url_group")
public class JurisdictionUserRelationUrlGroupModel implements Serializable {
    @Id
    private Integer uid;
    @Id
    private Integer gid;

    @ManyToOne(targetEntity = JurisdictionUrlGroupModel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "gid", referencedColumnName = "id", insertable = false, updatable = false)
    @Where(clause = "isDelete = 0")
    private JurisdictionUrlGroupModel urlGroup;
}
