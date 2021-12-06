package club.p6e.germ.cloud.gateway.cache;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface AuthCache {

    /** 缓存名称 */
    String NAME = "P6E:AUTH:USER_TOKEN:";
    String USER_NAME = "USER:";
    String DEVICE_NAME = "DEVICE:";
    String ACCESS_TOKEN_NAME = "ACCESS_TOKEN:";
    String REFRESH_TOKEN_NAME = "REFRESH_TOKEN:";

    /**
     * 读取认证数据
     * @param key 数据名称
     * @return 数据内容
     */
    public String getUser(String key);

    /**
     * 读取认证数据
     * @param key 数据名称
     * @return 数据内容
     */
    public String getAccessToken(String key);

    /**
     * 读取认证数据
     * @param key 数据名称
     * @return 数据内容
     */
    public String getRefreshToken(String key);



}
