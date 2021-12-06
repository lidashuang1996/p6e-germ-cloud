package club.p6e.germ.cloud.auth.domain.module.oauth2;
import club.p6e.germ.cloud.auth.domain.service.ServiceFactory;
import club.p6e.germ.cloud.auth.domain.keyvalue.ClientKeyValue;

/**
 * @author lidashuang
 * @version 1.0
 */
public class Oauth2ClientEntity {

    private final static String SCOPE_ALL = "ALL";
    private final ClientKeyValue client;

    public Oauth2ClientEntity(String clientId) {
        this.client = ServiceFactory.getOauth2ClientService().findByClientId(clientId);
    }

    /**
     * 验证该密码是否和账号匹配
     * @param secret 密码键值对
     * @return 是否匹配成功
     */
    public boolean verifySecret(String secret) {
        return client.getClientSecret().equals(secret);
    }

    public boolean verifyScope(String scope) {
        final String scopeTemplate = client.getClientScope();
        if (!SCOPE_ALL.equalsIgnoreCase(scopeTemplate)) {
            final String[] scopes = scope.split(",");
            final String[] rs = scopeTemplate.split(",");
            for (String s : scopes) {
                boolean bool = false;
                for (String r : rs) {
                    if (r.equals(s)) {
                        bool = true;
                        break;
                    }
                }
                if (!bool) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getScope() {
        return client.getClientScope();
    }

    public boolean verifyRedirectUri(String redirectUri) {
        final String redirectTemplate = client.getClientRedirectUri();
        final String[] rs = redirectTemplate.split(",");
        for (String r : rs) {
            if (redirectUri.equals(r)) {
                return true;
            }
        }
        return false;
    }

    public ClientKeyValue getClient() {
        return client;
    }
}
