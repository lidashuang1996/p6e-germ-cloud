package club.p6e.germ.message.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class CacheRedisGroup extends CacheRedis implements ICacheGroup {

    @Override
    public String get(String key) {
        return getStringRedisTemplate().opsForValue().get(GROUP_CACHE_NAME + key);
    }

    @Override
    public void set(String key, String value) {
        getStringRedisTemplate().opsForValue().set(GROUP_CACHE_NAME + key, value, GROUP_CACHE_TIME, TimeUnit.SECONDS);
    }

    @Override
    public long operationIncr(String key) {
        final Long count = getStringRedisTemplate().opsForValue().increment(GROUP_COUNTER_CACHE_NAME + key);
        return count == null ? 0 : count;
    }
}
