package club.p6e.germ.message.cache;

/**
 * 缓存-模版
 * @author lidashuang
 * @version 1.0
 */
public interface ICacheTemplate {

    /** 时间 */
    public final long TEMPLATE_CACHE_TIME = 10800;
    /** 名称 */
    public final String TEMPLATE_CACHE_NAME = "MESSAGE:CACHE:TEMPLATE:";

    /**
     * 读取模版缓存数据
     * @param key 键
     * @return 缓存数据
     */
    public String get(String key);

    /**
     * 写入模版缓存数据
     * @param key 键
     * @param value 值
     */
    public void set(String key, String value);
}
