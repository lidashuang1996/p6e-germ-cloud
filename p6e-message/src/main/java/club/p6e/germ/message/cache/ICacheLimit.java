package club.p6e.germ.message.cache;

import java.util.List;

/**
 * 缓存-组
 * @author lidashuang
 * @version 1.0
 */
public interface ICacheLimit {

    /** 时间 */
    public final long LIMIT_CACHE_TIME = 1000;
    /** 名称 */
    public final String LIMIT_CACHE_NAME = "MESSAGE:LIMIT:";


    /**
     * 删除组限流数据
     * @param key 键
     */
    public void del(String key);

    /**
     * 写入组限流数据
     * @param key 键
     * @param value 值
     * @return 生成的名称
     */
    public String set(String key, String value);

    /**
     * 读取组限流数据
     * @param key 键
     * @return 总数据的条数
     */
    public List<String> get(String key);
}
