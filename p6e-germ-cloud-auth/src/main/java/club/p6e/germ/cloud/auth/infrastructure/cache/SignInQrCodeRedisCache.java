package club.p6e.germ.cloud.auth.infrastructure.cache;

import club.p6e.germ.cloud.auth.infrastructure.cache.support.RedisCache;
import club.p6e.germ.cloud.auth.infrastructure.cache.support.TestRedisCache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class SignInQrCodeRedisCache implements RedisCache, SignInQrCodeCache {

    @Resource
    private TestRedisCache cache;

    @Override
    public void set(String key, String value) {
        cache.getStringRedisTemplate().opsForValue().set(NAME + key, value, TIME, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        return cache.getStringRedisTemplate().opsForValue().get(NAME + key);
    }

    @Override
    public void del(String key) {
        cache.getStringRedisTemplate().delete(NAME + key);
    }
}
