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
@Accessors(chain = true)
@Entity(name = "p6e_message_group")
public class MessagePlatformGroupModel implements Serializable {
    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String STATUS = "status";
    public static final String LIMIT = "limit";
    public static final String ROUTE = "route";
    public static final String NAME = "name";
    public static final String DESCRIBE = "describe";
    public static final String CREATE_DATE = "createDate";
    public static final String UPDATE_DATE = "updateDate";
    public static final String OPERATE = "operate";
    public static final String IS_DELETE = "isDelete";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    @Column(name = "[status]")
    private Integer status;
    @Column(name = "[limit]")
    private String limit;
    private String route;
    @Column(name = "[name]")
    private String name;
    @Column(name = "[describe]")
    private String describe;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String operate;
    private Integer isDelete;
}
