package club.p6e.germ.cloud.auth.infrastructure.cache;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface SignInAccountCodeCache {

    /** 缓存名称 */
    String NAME = "P6E:AUTH:PUSH:";

    /** 缓存时间 3 分钟 */
    long TIME = 180;

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
    public List<String> get(String key);

    /**
     * 删除认证数据
     * @param key 数据名称
     */
    public void del(String key);

}
