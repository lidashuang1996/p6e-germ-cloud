package club.p6e.germ.cloud.auth.domain.service;

import club.p6e.germ.cloud.auth.domain.keyvalue.ClientKeyValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class Oauth2ClientServiceLocalImpl implements Oauth2ClientService {

    /** 缓存对象 */
    private final Map<String, ClientKeyValue> cache = new HashMap<>();

    public Oauth2ClientServiceLocalImpl() {
        cache.put("123", new ClientKeyValue(
                "123",
                "clientName",
                "clientIcon",
                "clientDescribe",
                "all",
                "123456",
                "http://127.0.0.1/cs"
        ));
    }

    public void set(String clientId, ClientKeyValue client) {
        synchronized (cache) {
            cache.put(clientId, client);
        }
    }

    @Override
    public ClientKeyValue findByClientId(String clientId) {
        if (cache.get(clientId) != null) {
            return cache.get(clientId);
        }
        return null;
    }

}
