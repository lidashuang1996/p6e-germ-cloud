package club.p6e.germ.cloud.auth.domain.module.sign;

import club.p6e.germ.cloud.auth.infrastructure.cache.CacheFactory;
import club.p6e.germ.cloud.auth.domain.keyvalue.CodeKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.UserKeyValue;
import club.p6e.germ.cloud.auth.domain.service.ServiceFactory;
import com.p6e.germ.common.utils.P6eGeneratorUtil;

/**
 * @author lidashuang
 * @version 1.0
 */
public class SignInQrCodeEntity {

    private final CodeKeyValue code;
    private final String data;

    public static SignInQrCodeEntity generate() {
        final String uuid = P6eGeneratorUtil.uuid();
        CacheFactory.getSignInQrCodeCache().set(uuid, "");
        return new SignInQrCodeEntity(new CodeKeyValue(uuid));
    }

    public SignInQrCodeEntity(CodeKeyValue code) {
        this.code = code;
        this.data = CacheFactory.getSignInQrCodeCache().get(code.getContent());
    }

    public CodeKeyValue getCode() {
        return code;
    }

    public boolean isExist() {
        return data != null;
    }

    public UserKeyValue getUser() {
        if (data == null || data.isEmpty()) {
            return null;
        } else {
            try {
                return ServiceFactory.getUserService().findById(Integer.valueOf(data));
            } finally {
                CacheFactory.getSignInQrCodeCache().del(code.getContent());
            }
        }
    }

    public void setUser(String id) {
        CacheFactory.getSignInQrCodeCache().set(code.getContent(), id);
    }
}
