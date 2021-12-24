package club.p6e.germ.jurisdiction.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Entity(name = "p6e_jurisdiction_url_group")
@Accessors(chain = true)
public class JurisdictionUrlGroupModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String weight;
    private String name;
    private String describe;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String operate;
    private Integer isDelete;

    @OneToMany(targetEntity = JurisdictionUrlGroupRelationUrlModel.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "gid", referencedColumnName = "id", insertable = false, updatable = false)
    private List<JurisdictionUrlGroupRelationUrlModel> urls = new ArrayList<>();

}
