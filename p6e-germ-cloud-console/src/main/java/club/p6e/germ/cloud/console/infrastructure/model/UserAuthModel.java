package club.p6e.germ.cloud.console.infrastructure.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Entity(name = "p6e_user_auth")
public class UserAuthModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String phone;
    private String password;
    private String qq;
    private String wechat;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String operate;
}
