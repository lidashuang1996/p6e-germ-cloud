package club.p6e.germ.jurisdiction.cache;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class CacheRedisUser extends CacheRedis implements ICacheUser {

    @Override
    public void set(String name, Map<String, String> value) {
        final byte[] key = (JURISDICTION_USER_NAME + name).getBytes(StandardCharsets.UTF_8);
        getStringRedisTemplate().execute((RedisCallback<Object>) redisConnection -> {
            redisConnection.del(key);
            for (final String k : value.keySet()) {
                final String v = value.get(k);
                redisConnection.hSet(key, k.getBytes(StandardCharsets.UTF_8), v.getBytes(StandardCharsets.UTF_8));
            }
            redisConnection.expire(key, JURISDICTION_USER_TIME);
            return null;
        });
    }

    @Override
    public String get(String name, String key) {
        final Object r = getStringRedisTemplate().opsForHash().get(JURISDICTION_USER_NAME + name, key);
        return r == null ? null : String.valueOf(r);
    }

    @Override
    public void del(String name) {
        getStringRedisTemplate().delete(JURISDICTION_USER_NAME + name);
    }

    @Override
    public boolean hasKey(String name) {
        final Boolean r = getStringRedisTemplate().hasKey(JURISDICTION_USER_NAME + name);
        return r != null && r;
    }
}
