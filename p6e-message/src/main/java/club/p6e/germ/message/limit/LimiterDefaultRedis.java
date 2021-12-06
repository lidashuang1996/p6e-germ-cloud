package club.p6e.germ.message.limit;

import club.p6e.germ.message.cache.ICacheLimit;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component("LimiterDefaultRedis")
public class LimiterDefaultRedis extends LimiterAbstract implements LimiterInterface {

    /** 缓存服务 */
    @Resource
    private ICacheLimit cache;

    @Override
    public boolean verification(String limit, String type, String id) {
        final String key = type + ":" + id;
        return dispose(limit, cache.get(key));
    }

    @Override
    public LimiterRollBackInterface incr(String type, String id) {
        final String key = cache.set(type + ":" + id, "{\"type\": " + type + ", \"id\": " + id + "}");
        return () -> cache.del(key);
    }
}
