package club.p6e.germ.cloud.auth.infrastructure.cache;

import club.p6e.germ.cloud.auth.infrastructure.cache.support.RedisCache;
import club.p6e.germ.cloud.auth.infrastructure.cache.support.TestRedisCache;
import com.p6e.germ.common.utils.P6eGeneratorUtil;
import com.p6e.germ.common.utils.P6eJsonUtil;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class SignUserTokenRedisCache implements RedisCache, SignUserTokenCache {

    @Resource
    private TestRedisCache cache;

    @Override
    public String[] set(String uid, String device, String content) {
        final String[] r = new String[] {
                DigestUtils.md5DigestAsHex((uid + P6eGeneratorUtil.uuid()).getBytes(StandardCharsets.UTF_8)),
                DigestUtils.md5DigestAsHex((uid + P6eGeneratorUtil.uuid()).getBytes(StandardCharsets.UTF_8)),
        };
        final String mContent = uid + "," + device + "," + r[0] + "," + r[1];
        return cache.getStringRedisTemplate().execute((RedisCallback<String[]>) connection -> {
            // 写入用户信息
            connection.set((NAME + USER_NAME + uid).getBytes(StandardCharsets.UTF_8),
                    content.getBytes(StandardCharsets.UTF_8), Expiration.from(TIME, TimeUnit.SECONDS), RedisStringCommands.SetOption.upsert());
            // 添加设备的数据
            connection.set((NAME + DEVICE_NAME + uid + ":" + device + ":" + ACCESS_TOKEN_NAME + r[0]).getBytes(StandardCharsets.UTF_8),
                    mContent.getBytes(StandardCharsets.UTF_8), Expiration.from(TIME, TimeUnit.SECONDS), RedisStringCommands.SetOption.upsert());
            connection.set((NAME + DEVICE_NAME + uid + ":" + device + ":" + REFRESH_TOKEN_NAME + r[1]).getBytes(StandardCharsets.UTF_8),
                    mContent.getBytes(StandardCharsets.UTF_8), Expiration.from(TIME, TimeUnit.SECONDS), RedisStringCommands.SetOption.upsert());
            connection.set((NAME + ACCESS_TOKEN_NAME + r[0]).getBytes(StandardCharsets.UTF_8),
                    mContent.getBytes(StandardCharsets.UTF_8), Expiration.from(TIME, TimeUnit.SECONDS), RedisStringCommands.SetOption.upsert());
            connection.set((NAME + REFRESH_TOKEN_NAME + r[1]).getBytes(StandardCharsets.UTF_8),
                    mContent.getBytes(StandardCharsets.UTF_8), Expiration.from(TIME, TimeUnit.SECONDS), RedisStringCommands.SetOption.upsert());
            // 删除设备的数据
            conditionClean(connection, (NAME + DEVICE_NAME + uid + ":" + device + ":*"));
            return r;
        });
    }

    @Override
    public Map<String, String> getUser(String uid) {
        final String content = cache.getStringRedisTemplate().opsForValue().get(NAME + USER_NAME + uid);
        if (content != null) {
            return P6eJsonUtil.fromJsonToMap(content, String.class, String.class);
        }
        return null;
    }

    @Override
    public String[] getAccessToken(String key) {
        return getContent(ACCESS_TOKEN_NAME + key);
    }

    @Override
    public String[] getRefreshToken(String key) {
        return getContent(REFRESH_TOKEN_NAME + key);
    }

    @Override
    public void del(String key) {
        cache.getStringRedisTemplate().execute((RedisCallback<Object>) connection -> {
            final byte[] bs = connection.get((NAME + ACCESS_TOKEN_NAME + key).getBytes(StandardCharsets.UTF_8));
            if (bs == null) {
                return false;
            } else {
                final int len = 4;
                final String[] cs = new String(bs, StandardCharsets.UTF_8).split(",");
                if (cs.length == len) {
                    connection.del((NAME + DEVICE_NAME + cs[0] + ":" + cs[1] + ":" + ACCESS_TOKEN_NAME + cs[2]).getBytes(StandardCharsets.UTF_8));
                    connection.del((NAME + DEVICE_NAME + cs[0] + ":" + cs[1] + ":" + REFRESH_TOKEN_NAME + cs[3]).getBytes(StandardCharsets.UTF_8));
                    connection.del((NAME + ACCESS_TOKEN_NAME + cs[2]).getBytes(StandardCharsets.UTF_8));
                    connection.del((NAME + REFRESH_TOKEN_NAME + cs[3]).getBytes(StandardCharsets.UTF_8));
                }
            }
            return true;
        });
    }

    @Override
    public void clean(String uid) {
        cache.getStringRedisTemplate().execute((RedisCallback<Object>) connection -> {
            connection.del((NAME + USER_NAME + uid).getBytes(StandardCharsets.UTF_8));
            conditionClean(connection, (NAME + DEVICE_NAME + uid + ":*"));
            return true;
        });
    }


    private String[] getContent(String match) {
        return cache.getStringRedisTemplate().execute((RedisCallback<String[]>) connection -> {
            final byte[] bs = connection.get((NAME + match).getBytes(StandardCharsets.UTF_8));
            if (bs == null) {
                return null;
            } else {
                final String[] cs = new String(bs, StandardCharsets.UTF_8).split(",");
                final byte[] rs = connection.get((NAME + USER_NAME + cs[0]).getBytes(StandardCharsets.UTF_8));
                return rs == null ? null : new String[]{ new String(rs, StandardCharsets.UTF_8), cs[0], cs[1], cs[2], cs[3] };
            }
        });
    }

    /**
     * 根据条件清除数据
     * @param connection 连接对象
     * @param match 搜索条件
     */
    private void conditionClean (RedisConnection connection, String match) {
        final Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match(match).count(100).build());
        cursor.forEachRemaining(item -> {
            final byte[] bs = connection.get(item);
            connection.del(bs);
            if (bs != null) {
                final int minLen = 2;
                final String s = new String(bs);
                final String[] ss = s.split(":");
                if (ss.length > minLen) {
                    connection.del((NAME + ss[ss.length - 2] + ":" + ss[ss.length - 1]).getBytes(StandardCharsets.UTF_8));
                }
            }
        });
    }

}
