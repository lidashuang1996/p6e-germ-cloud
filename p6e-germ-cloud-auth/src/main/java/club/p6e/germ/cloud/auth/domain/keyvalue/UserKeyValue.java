package club.p6e.germ.cloud.auth.domain.keyvalue;

import com.p6e.germ.common.utils.P6eJsonUtil;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Getter
public class UserKeyValue extends HashMap<String, String> {

    /** 跳过符号 */
    private static final String SKIP_CHAR = "$";

    /** 用户编号 */
    private String id;
    /** 用户账号 */
    private String account;
    /** 用户密码 */
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /** 导出 */
    public String export() {
        final Map<String, String> e = new HashMap<>(16);
        e.put("id", id);
        e.put("account", account);
        for (final String key : keySet()) {
            if (!key.startsWith(SKIP_CHAR)) {
                e.put(key, get(key));
            }
        }
        return P6eJsonUtil.toJson(e);
    }
}
