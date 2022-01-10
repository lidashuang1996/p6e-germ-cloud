package club.p6e.germ.cloud.console.infrastructure.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
@Entity(name = "p6e_message_platform")
public class MessagePlatformModel implements Serializable {
    public static final String ID = "id";
    public static final String WEIGHT = "weight";
    public static final String STATUS = "status";
    public static final String LIMIT = "limit";
    public static final String CONFIG = "config";
    public static final String ACTUATOR = "actuator";
    public static final String NAME = "name";
    public static final String DESCRIBE = "describe";
    public static final String CREATE_DATE = "createDate";
    public static final String UPDATE_DATE = "updateDate";
    public static final String OPERATE = "operate";
    public static final String IS_DELETE = "isDelete";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer weight;
    private Integer status;
    private String limit;
    private String config;
    private String actuator;
    private String name;
    private String describe;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String operate;
    private Integer isDelete;
}