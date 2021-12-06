package club.p6e.germ.cloud.gateway.cache;

import club.p6e.germ.cloud.gateway.cache.support.RedisCache;
import club.p6e.germ.cloud.gateway.cache.support.TestRedisCache;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class AuthRedisCache implements RedisCache, AuthCache {

    @Resource
    private TestRedisCache cache;

    @Override
    public String getUser(String key) {
        return cache.getStringRedisTemplate().opsForValue().get(NAME + USER_NAME + key);
    }

    @Override
    public String getAccessToken(String key) {
        final String[] content = getContent(ACCESS_TOKEN_NAME + key);
        return content == null ? null : content[0];
    }

    @Override
    public String getRefreshToken(String key) {
        final String[] content = getContent(REFRESH_TOKEN_NAME + key);
        return content == null ? null : content[0];
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
}
