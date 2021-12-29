package club.p6e.germ.cloud.console.infrastructure.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Entity(name = "p6e_user")
@Accessors(chain = true)
public class UserModel implements Serializable {
    public static final String ID = "id";
    public static final String STATUS = "status";
    public static final String NAME = "name";
    public static final String NICKNAME = "nickname";
    public static final String AVATAR = "avatar";
    public static final String DESCRIBE = "describe";
    public static final String AGE = "age";
    public static final String SEX = "sex";
    public static final String BIRTHDAY = "birthday";
    public static final String CREATE_DATE = "createDate";
    public static final String UPDATE_DATE = "updateDate";
    public static final String OPERATE = "operate";
    public static final String IS_DELETE = "isDelete";
    public static final String AUTH = "auth";
    public static final String AUTH_EMAIL = "email";
    public static final String AUTH_PHONE = "phone";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer status;
    private String name;
    private String nickname;
    private String avatar;
    private String describe;
    private Integer age;
    private String sex;
    private LocalDate birthday;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String operate;
    private Integer isDelete;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private UserAuthModel auth;
}
