package club.p6e.germ.cloud.auth.domain.module.oauth2;

import club.p6e.germ.cloud.auth.domain.keyvalue.CodeKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.TokenKeyValue;
import com.p6e.germ.common.utils.P6eGeneratorUtil;

/**
 * @author lidashuang
 * @version 1.0
 */
public class Oauth2CodeEntity {

    private CodeKeyValue code;

    public static Oauth2CodeEntity generate() {
        try {
            final String m = P6eGeneratorUtil.uuid();
            return new Oauth2CodeEntity(new CodeKeyValue(m));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Oauth2CodeEntity(CodeKeyValue value) {

    }

    public CodeKeyValue getCode() {
        return code;
    }

    public TokenKeyValue getData() {
        return  null;
    }

    public String getScope() {
        return null;
    }
}
