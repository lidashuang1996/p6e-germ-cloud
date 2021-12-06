package club.p6e.germ.cloud.auth.infrastructure.cache;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface SignInQrCodeCache {

    /** 缓存名称 */
    String NAME = "P6E:AUTH:QR_CODE:";

    /** 缓存时间 1分钟 */
    long TIME = 90;

    /**
     * 写入认证数据
     * @param key 数据名称
     * @param value 数据内容
     */
    public void set(String key, String value);

    /**
     * 读取认证数据
     * @param key 数据名称
     * @return 数据内容
     */
    public String get(String key);

    /**
     * 删除认证数据
     * @param key 数据名称
     */
    public void del(String key);

}
