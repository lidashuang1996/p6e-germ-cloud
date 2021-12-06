package club.p6e.germ.cloud.auth.infrastructure.cache;

import club.p6e.germ.cloud.auth.infrastructure.cache.support.RedisCache;
import club.p6e.germ.cloud.auth.infrastructure.cache.support.TestRedisCache;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class SignInAccountCodeRedisCache implements RedisCache, SignInAccountCodeCache {

    @Resource
    private TestRedisCache cache;

    @Override
    public void set(String key, String value) {
        cache.getStringRedisTemplate().opsForValue().set(NAME + key, value, TIME, TimeUnit.SECONDS);
    }

    @Override
    public List<String> get(String key) {
        return cache.getStringRedisTemplate().execute((RedisCallback<List<String>>) connection -> {
            final List<String> r = new ArrayList<>();
            final Cursor<byte[]> cursor = connection.scan(
                    ScanOptions.scanOptions().match(NAME + key + "*").count(20).build());
            cursor.forEachRemaining(item -> {
                final byte[] bs = connection.get(item);
                if (bs != null) {
                    r.add(new String(bs, StandardCharsets.UTF_8));
                }
            });
            return r;
        });
    }

    @Override
    public void del(String key) {
        cache.getStringRedisTemplate().execute((RedisCallback<Object>) connection -> {
            final Cursor<byte[]> cursor = connection.scan(
                    ScanOptions.scanOptions().match(NAME + key + "*").count(100).build());
            cursor.forEachRemaining(item -> {
                final byte[] bs = connection.get(item);
                if (bs != null) {
                    connection.del(bs);
                }
            });
            return true;
        });
    }
}
