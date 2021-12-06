package club.p6e.germ.cloud.auth.domain.module.sign;

import club.p6e.germ.cloud.auth.infrastructure.cache.CacheFactory;
import club.p6e.germ.cloud.auth.domain.keyvalue.MarkKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.OtherStateTypeKeyValue;
import com.p6e.germ.common.utils.P6eGeneratorUtil;

/**
 * @author lidashuang
 * @version 1.0
 */
public class SignInOtherStateEntity {

    private final MarkKeyValue mark;
    private final OtherStateTypeKeyValue type;

    public static SignInOtherStateEntity generate(OtherStateTypeKeyValue type, String cacheContent) {
        try {
            final String uuid = P6eGeneratorUtil.uuid();
            CacheFactory.getSignInOtherCodeCache().set(uuid, type.name(), cacheContent);
            return new SignInOtherStateEntity(type, new MarkKeyValue(uuid));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SignInOtherStateEntity(OtherStateTypeKeyValue type, MarkKeyValue mark) {
        this.mark = mark;
        this.type = type;
        final String content = CacheFactory.getSignInOtherCodeCache().get(mark.getContent(), type.name());
        if (content != null) {
            return;
        }
        throw new RuntimeException(this.getClass() + " publicKey, privateKey is null error.");
    }

    public MarkKeyValue getMark() {
        return mark;
    }

    public OtherStateTypeKeyValue getType() {
        return type;
    }

    public String getCache() {
        return CacheFactory.getSignInOtherCodeCache().get(mark.getContent(), type.name());
    }

    public void deleteCache() {
        CacheFactory.getSignInOtherCodeCache().del(mark.getContent(), type.name());
    }
}
