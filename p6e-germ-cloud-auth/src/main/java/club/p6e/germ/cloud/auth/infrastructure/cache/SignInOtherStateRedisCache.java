package club.p6e.germ.cloud.auth.infrastructure.cache;

import club.p6e.germ.cloud.auth.infrastructure.cache.support.RedisCache;
import club.p6e.germ.cloud.auth.infrastructure.cache.support.TestRedisCache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class SignInOtherStateRedisCache implements RedisCache, SignInOtherStateCache {

    @Resource
    private TestRedisCache cache;

    @Override
    public void set(String key, String type, String value) {
        cache.getStringRedisTemplate().opsForValue().set(NAME + type + ":" + key, value, TIME);
    }

    @Override
    public String get(String key, String type) {
        return cache.getStringRedisTemplate().opsForValue().get(NAME + type + ":" + key);
    }

    @Override
    public void del(String key, String type) {
        cache.getStringRedisTemplate().delete(NAME + type + ":" + key);
    }
}
