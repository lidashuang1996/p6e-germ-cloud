package club.p6e.germ.cloud.gateway.auth;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 认证的模型
 * @author lidashuang
 * @version 1.0
 */
@Data
@Accessors(chain = true)
public class AuthModel implements Serializable {
    private Integer id;
    private Integer state;
    private Integer role;
    private String name;
    private String nickname;
    private String avatar;
    private String describe;
    private String email;
    private String phone;
    private List<String> jurisdictions = new ArrayList<>();
}
