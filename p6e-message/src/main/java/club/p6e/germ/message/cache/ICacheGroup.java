package club.p6e.germ.message.cache;

import java.util.List;

/**
 * 缓存-组
 * @author lidashuang
 * @version 1.0
 */
public interface ICacheGroup {

    /** 时间 */
    public final long GROUP_CACHE_TIME = 10800;
    /** 名称 */
    public final String GROUP_CACHE_NAME = "MESSAGE:CACHE:GROUP:";

    /** 名称 */
    public final String GROUP_COUNTER_CACHE_NAME = "MESSAGE:COUNTER:";

    /**
     * 读取组缓存数据
     * @param key 键
     * @return 缓存数据
     */
    public String get(String key);

    /**
     * 写入组缓存数据
     * @param key 键
     * @param value 值
     */
    public void set(String key, String value);

    /**
     * 操作计数器累加
     * @param key 键
     * @return 累加后的数据
     */
    public long operationIncr(String key);
}
