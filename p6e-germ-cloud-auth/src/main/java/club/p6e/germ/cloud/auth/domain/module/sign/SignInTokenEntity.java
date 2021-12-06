package club.p6e.germ.cloud.auth.domain.module.sign;

import club.p6e.germ.cloud.auth.infrastructure.cache.CacheFactory;
import club.p6e.germ.cloud.auth.domain.keyvalue.DeviceKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.TokenKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.UserKeyValue;
import com.p6e.germ.common.utils.P6eJsonUtil;

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class SignInTokenEntity {
    public static final DeviceKeyValue GRANT_AUTH_CODE_DEVICE = new DeviceKeyValue("GRANT_AUTH_CODE_DEVICE");
    public static final DeviceKeyValue GRANT_AUTH_PASSWORD_DEVICE = new DeviceKeyValue("GRANT_AUTH_PASSWORD_DEVICE");

    public static final long EXPIRES_IN = 3600L;
    public static final String TOKEN_TYPE = "Bearer";

    private final String uid;
    private final TokenKeyValue token;

    private final DeviceKeyValue device;
    private Map<String, String> user;

    public static SignInTokenEntity generate(UserKeyValue user, DeviceKeyValue device) {
        final String[] ts = CacheFactory.getSignUserTokenCache().set(user.getId(), device.getContent(), user.export());
        return new SignInTokenEntity(user.getId(), new TokenKeyValue(ts[0], ts[1]), device);
    }

    public static SignInTokenEntity accessToken(String token) {
        final String[] ts = CacheFactory.getSignUserTokenCache().getAccessToken(token);
        if (ts == null || ts[3] == null || ts[4] == null) {
            throw new RuntimeException();
        }
        return new SignInTokenEntity(ts[1], new TokenKeyValue(ts[3], ts[4]), new DeviceKeyValue(ts[2]));
    }

    public static SignInTokenEntity refreshToken(String token) {
        final String[] ts = CacheFactory.getSignUserTokenCache().getRefreshToken(token);
        if (ts == null || ts[3] == null || ts[4] == null) {
            throw new RuntimeException();
        }
        return new SignInTokenEntity(ts[1], new TokenKeyValue(ts[3], ts[4]), new DeviceKeyValue(ts[2]));
    }

    public SignInTokenEntity(String uid, TokenKeyValue token, DeviceKeyValue device) {
        this.uid = uid;
        this.token = token;
        this.device = device;
    }

    public TokenKeyValue getData() {
        return token;
    }

    public Map<String, String> getUser() {
        if (user == null) {
            user = CacheFactory.getSignUserTokenCache().getUser(uid);
        }
        return user;
    }

    public void remove() {
        CacheFactory.getSignUserTokenCache().del(token.getAccessToken());
    }

    public void clean() {
        CacheFactory.getSignUserTokenCache().clean(uid);
    }

    public SignInTokenEntity refresh() {
        this.remove();
        final String[] ts = CacheFactory.getSignUserTokenCache().set(uid, device.getContent(), P6eJsonUtil.toJson(user));
        return new SignInTokenEntity(uid, new TokenKeyValue(ts[0], ts[1]), device);
    }
}
