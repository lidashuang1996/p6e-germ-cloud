package club.p6e.germ.cloud.auth.domain.keyvalue;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Getter
public class ClientKeyValue {
    private String clientId;
    private String clientName;
    private String clientIcon;
    private String clientDescribe;

    private String clientScope;
    private String clientSecret;
    private String clientRedirectUri;

    public ClientKeyValue(String clientId, String clientName, String clientIcon, String clientDescribe, String clientScope, String clientSecret, String clientRedirectUri) {
        this.clientId = clientId;
        this.clientName = clientName;
        this.clientIcon = clientIcon;
        this.clientDescribe = clientDescribe;
        this.clientScope = clientScope;
        this.clientSecret = clientSecret;
        this.clientRedirectUri = clientRedirectUri;
    }

    /**
     * 构造方法 Map 初始化
     * @param map Map 对象
     */
    public ClientKeyValue(Map<String, String> map) {
        if (map != null
                &&  map.get("clientId") != null
                &&  map.get("clientName") != null
                &&  map.get("clientIcon") != null
                &&  map.get("clientDescribe") != null) {
            this.clientId = map.get("clientId");
            this.clientName = map.get("clientName");
            this.clientIcon = map.get("clientIcon");
            this.clientDescribe = map.get("clientDescribe");
        }
    }

    /**
     * 输出为 Map 对象
     * @return Map 对象
     */
    public Map<String, String> toMap() {
        Map<String, String> m = new HashMap<>();
        m.put("clientId", this.clientId);
        m.put("clientName", this.clientName);
        m.put("clientIcon", this.clientIcon);
        m.put("clientDescribe", this.clientDescribe);
        return m;
    }
}
