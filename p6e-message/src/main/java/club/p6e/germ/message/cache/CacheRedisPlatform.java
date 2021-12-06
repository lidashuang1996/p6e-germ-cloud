package club.p6e.germ.message.cache;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class CacheRedisPlatform extends CacheRedis implements ICacheLimit {

    /** 日期时间格式化对象 */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Override
    public void del(String key) {
        getStringRedisTemplate().delete(LIMIT_CACHE_NAME + key);
    }

    @Override
    public String set(String key, String value) {
        key =  key + ":" + DATE_TIME_FORMATTER.format(LocalDateTime.now());
        getStringRedisTemplate().opsForValue().set(LIMIT_CACHE_NAME + key, value, LIMIT_CACHE_TIME, TimeUnit.SECONDS);
        return key;
    }

    @Override
    public List<String> get(String key) {
        final String search = LIMIT_CACHE_NAME + key + ":*";
        return getStringRedisTemplate().execute((RedisCallback<List<String>>) redisConnection -> {
            final List<String> list = new ArrayList<>();
            final Set<byte[]> set = redisConnection.keys(search.getBytes(StandardCharsets.UTF_8));
            if (set != null && set.size() > 0) {
                for (byte[] bytes : set) {
                    list.add(new String(bytes));
                }
            }
            return list;
        });
    }
}
