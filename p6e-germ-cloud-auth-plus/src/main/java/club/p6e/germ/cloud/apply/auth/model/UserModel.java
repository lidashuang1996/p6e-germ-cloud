package club.p6e.germ.cloud.apply.auth.model;

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
