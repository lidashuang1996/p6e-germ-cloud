package club.p6e.germ.message.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class CacheRedisTemplate extends CacheRedis implements ICacheTemplate {

    @Override
    public String get(String key) {
        return getStringRedisTemplate().opsForValue().get(TEMPLATE_CACHE_NAME + key);
    }

    @Override
    public void set(String key, String value) {
        getStringRedisTemplate().opsForValue().set(TEMPLATE_CACHE_NAME + key, value, TEMPLATE_CACHE_TIME, TimeUnit.SECONDS);
    }
}
