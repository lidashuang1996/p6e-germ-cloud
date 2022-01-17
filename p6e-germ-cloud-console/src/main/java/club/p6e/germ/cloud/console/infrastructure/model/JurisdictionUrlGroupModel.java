package club.p6e.germ.cloud.console.infrastructure.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Entity(name = "p6e_jurisdiction_url_group")
@Accessors(chain = true)
public class JurisdictionUrlGroupModel implements Serializable {
    public static final String ID = "id";
    public static final String WEIGHT = "weight";
    public static final String NAME = "name";
    public static final String DESCRIBE = "describe";
    public static final String CREATE_DATE = "create_date";
    public static final String UPDATE_DATE = "update_date";
    public static final String OPERATE = "operate";
    public static final String IS_DELETE = "isDelete";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer weight;
    private String name;
    private String describe;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String operate;
    private Integer isDelete;

//    @OneToMany(targetEntity = JurisdictionUrlGroupRelationUrlModel.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "gid", referencedColumnName = "id", insertable = false, updatable = false)
//    private List<JurisdictionUrlGroupRelationUrlModel> urls = new ArrayList<>();

}
