package club.p6e.germ.message.cache;

import com.p6e.germ.cache.redis.P6eCacheRedisAbstract;

/**
 * 缓存的实现
 * @author lidashuang
 * @version 1.0
 */
public class CacheRedis extends P6eCacheRedisAbstract implements ICache {

    /**
     * 采用的缓存类型
     * @return 缓存类型
     */
    @Override
    public String toType() {
        return "REDIS_TYPE";
    }

}
