package club.p6e.germ.cloud.auth.infrastructure.cache;

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface SignUserTokenCache {

    /** 缓存名称 */
    String NAME = "P6E:AUTH:USER_TOKEN:";
    String USER_NAME = "USER:";
    String DEVICE_NAME = "DEVICE:";
    String ACCESS_TOKEN_NAME = "ACCESS_TOKEN:";
    String REFRESH_TOKEN_NAME = "REFRESH_TOKEN:";

    /** 缓存时间 2 小时 */
    long TIME = 7200;

    /**
     * 写入认证数据
     * @param uid 用户编号
     * @param device 设备名称
     * @param content 缓存内容
     * @return 缓存的放回内容
     */
    public String[] set(String uid, String device, String content);

    /**
     * 读取认证数据
     * @param token 数据名称
     * @return 数据内容
     */
    public Map<String, String> getUser(String uid);

    /**
     * 读取认证数据
     * @param token 数据名称
     * @return 数据内容
     */
    public String[] getAccessToken(String token);

    /**
     * 读取认证数据
     * @param token 数据名称
     * @return 数据内容
     */
    public String[] getRefreshToken(String token);

    /**
     * 删除认证数据
     * @param token 数据名称
     */
    public void del(String token);

    /**
     * 清除数据
     * @param uid 用户编号
     */
    public void clean(String uid);
}
