package club.p6e.germ.jurisdiction.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class CacheRedisUrl extends CacheRedis implements ICacheUrl {

    @Override
    public void set(String key, String value) {
        getStringRedisTemplate().opsForValue().set(
                JURISDICTION_CACHE_URL_NAME + key, value, JURISDICTION_CACHE_URL_TIME, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        return getStringRedisTemplate().opsForValue().get(JURISDICTION_CACHE_URL_NAME + key);
    }
}
