package club.p6e.germ.cloud.apply.auth.service;

import club.p6e.germ.cloud.auth.domain.keyvalue.ClientKeyValue;
import club.p6e.germ.cloud.auth.domain.service.Oauth2ClientService;
import org.springframework.stereotype.Component;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class Oauth2ClientServiceDatabaseImpl implements Oauth2ClientService {

    @Override
    public ClientKeyValue findByClientId(String clientId) {
        return null;
    }

}
