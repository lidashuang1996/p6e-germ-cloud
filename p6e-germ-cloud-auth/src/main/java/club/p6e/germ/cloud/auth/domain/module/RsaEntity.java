package club.p6e.germ.cloud.auth.domain.module;

import club.p6e.germ.cloud.auth.infrastructure.cache.CacheFactory;
import club.p6e.germ.cloud.auth.domain.keyvalue.MarkKeyValue;
import com.p6e.germ.common.utils.P6eGeneratorUtil;
import com.p6e.germ.common.utils.P6eJsonUtil;
import com.p6e.germ.common.utils.P6eRsaUtil;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class RsaEntity {

    /** 公钥名称 */
    private static final String PUBLIC_KEY_NAME = "publicKey";
    /** 私钥名称 */
    private static final String PRIVATE_KEY_NAME = "privateKey";

    private final String publicKey;
    private final String privateKey;
    private final MarkKeyValue mark;

    public static RsaEntity generate() {
        try {
            final String mark = P6eGeneratorUtil.uuid();
            final KeyPair keyPair = P6eRsaUtil.initKey();
            final String publicKey = P6eRsaUtil.getPublicKey(keyPair);
            final String privateKey = P6eRsaUtil.getPrivateKey(keyPair);
            final Map<String, String> map = new HashMap<>(2);
            map.put(PUBLIC_KEY_NAME, publicKey);
            map.put(PRIVATE_KEY_NAME, privateKey);
            CacheFactory.getSignInRsaCache().set(mark, P6eJsonUtil.toJson(map));
            return new RsaEntity(new MarkKeyValue(mark));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public RsaEntity(MarkKeyValue mark) {
        this.mark = mark;
        final String content = CacheFactory.getSignInRsaCache().get(mark.getContent());
        if (content != null) {
            final Map<String, String> map = P6eJsonUtil.fromJsonToMap(content, String.class, String.class);
            if (map != null && map.get(PUBLIC_KEY_NAME) != null && map.get(PRIVATE_KEY_NAME) != null) {
                this.publicKey = map.get(PUBLIC_KEY_NAME);
                this.privateKey = map.get(PRIVATE_KEY_NAME);
                return;
            }
        }
        throw new RuntimeException(this.getClass() + " publicKey, privateKey is null error.");
    }

    public String decryption(String content) {
        try {
            return P6eRsaUtil.decrypt(P6eRsaUtil.loadingPrivateKey(privateKey), content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getPublicKey() {
        return publicKey;
    }

    public MarkKeyValue getMark() {
        return mark;
    }
}
