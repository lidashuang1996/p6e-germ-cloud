package club.p6e.germ.jurisdiction.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class CacheRedisTask extends CacheRedis implements ICacheTask {

    @Override
    public long index() {
        final String r = getStringRedisTemplate().opsForValue().get(JURISDICTION_TASK_INDEX_NAME);
        return r == null ? 0: Long.parseLong(r);
    }

    @Override
    public long indexIncr() {
        final Long r = getStringRedisTemplate().opsForValue().increment(JURISDICTION_TASK_INDEX_NAME);
        return r == null ? -1 : r;
    }

    @Override
    public boolean lock(long index) {
        final String key = JURISDICTION_TASK_LOCK_NAME + index;
        final Long r = getStringRedisTemplate().opsForValue().increment(key);
        if (r != null && r == 1) {
            getStringRedisTemplate().expire(key, JURISDICTION_TASK_LOCK_TIME, TimeUnit.SECONDS);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void renewalLock(long index) {
        getStringRedisTemplate().expire(JURISDICTION_TASK_LOCK_NAME + index, JURISDICTION_TASK_LOCK_TIME, TimeUnit.SECONDS);
    }

    @Override
    public void releaseLock(long index) {
        getStringRedisTemplate().delete(JURISDICTION_TASK_LOCK_NAME + index);
    }
}
