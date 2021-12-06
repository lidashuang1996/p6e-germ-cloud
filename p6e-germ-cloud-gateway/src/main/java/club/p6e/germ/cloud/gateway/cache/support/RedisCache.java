package club.p6e.germ.cloud.gateway.cache.support;

/**
 * Redis 实现缓存
 * @author lidashuang
 * @version 1.0
 */
public interface RedisCache extends Cache {

    /**
     * 默认实现输入缓存的类型
     * @return 缓存的类型
     */
    @Override
    default String toType() {
        return "REDIS";
    }
}