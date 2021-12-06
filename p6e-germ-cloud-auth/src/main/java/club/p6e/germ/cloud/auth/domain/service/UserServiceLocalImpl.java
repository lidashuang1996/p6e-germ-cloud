package club.p6e.germ.cloud.auth.domain.service;

import club.p6e.germ.cloud.auth.domain.keyvalue.UserKeyValue;
import com.p6e.germ.common.utils.P6eGeneratorUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class UserServiceLocalImpl implements UserService {

    /***
     * 默认的账号密码
     */
    private final Map<String, UserKeyValue> cache = new HashMap<>();

    public UserServiceLocalImpl() {
        final UserKeyValue user = new UserKeyValue();
        user.setId("1");
        user.setAccount("admin@qq.com");
        // 密码 123456
        user.setPassword("b5b0a1dddfc9fce04362b66d486d0c71e10adc39");
        user.put("sex", "男");
        user.put("name", "用户的名称");
        cache.put("1", user);
    }

    public void set(String uId, UserKeyValue user) {
        synchronized (cache) {
            cache.put(uId, user);
        }
    }

    @Override
    public UserKeyValue findById(Integer id) {
        if (cache.get(String.valueOf(id)) != null) {
            return cache.get(String.valueOf(id));
        } else {
            return null;
        }
    }

    @Override
    public UserKeyValue findByAccount(String account) {
        for (UserKeyValue value : cache.values()) {
            if (value.getAccount().equals(account)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public UserKeyValue create(String account, String code, String type) {
        final UserKeyValue user = new UserKeyValue();
        final String uid = P6eGeneratorUtil.random();
        user.setId(uid);
        user.setAccount(account);
        user.setPassword("");
        user.put("sex", "男");
        user.put("name", "用户的名称" + uid);
        user.put("code", "code");
        user.put("type", "type");
        cache.put(uid, user);
        return user;
    }

    @Override
    public UserKeyValue create(String account, String password) {
        final UserKeyValue user = new UserKeyValue();
        final String uid = P6eGeneratorUtil.random();
        user.setId(uid);
        user.setAccount(account);
        user.setPassword(password);
        user.put("sex", "男");
        user.put("name", "用户的名称" + account);
        cache.put(uid, user);
        return user;
    }

    @Override
    public UserKeyValue updatePassword(String account, String password) {
        for (UserKeyValue value : cache.values()) {
            if (value.getAccount().equals(account)) {
                value.setPassword(password);
                return value;
            }
        }
        return null;
    }

}
