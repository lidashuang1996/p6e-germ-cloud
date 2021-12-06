package club.p6e.germ.jurisdiction.task;

import club.p6e.germ.jurisdiction.cache.ICacheTask;
import club.p6e.germ.jurisdiction.cache.ICacheUrl;
import club.p6e.germ.jurisdiction.model.JurisdictionUrlDb;
import club.p6e.germ.jurisdiction.mybatis.mapper.JurisdictionUrlMapper;
import com.p6e.germ.common.P6eNotification;
import com.p6e.germ.common.utils.P6eSpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@Configuration
@EnableScheduling
public class DbToCacheTask {

    /** 数据库长度 */
    private static final int DB_SIZE = 100;
    /** 注入日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(DbToCacheTask.class);

    /** 注入缓存对象 */
    private final ICacheUrl urlCache;
    private final ICacheTask taskCache;
    /** 注入数据库对象 */
    private final JurisdictionUrlMapper urlMapper;

    @Autowired
    public DbToCacheTask(ICacheUrl urlCache, ICacheTask taskCache, JurisdictionUrlMapper urlMapper) {
        this.urlCache = urlCache;
        this.taskCache = taskCache;
        this.urlMapper = urlMapper;
    }

    @Scheduled(initialDelay = 10000, fixedRate = 1800000)
    public void run() {
        // 多台服务启动的时候只需要一台进行缓存操作
        // 通过 REDIS 实现的多台服务间锁
        final long index = taskCache.index();
        // 获取锁
        if (!taskCache.lock(index)) {
            return;
        }

        LOGGER.info("start run task [ DbToCacheTask ].");

        // 组数据缓存
        int groupPage = 1;
        List<JurisdictionUrlDb> urls;
        final JurisdictionUrlDb urlDb = new JurisdictionUrlDb();
        urlDb.setSize(DB_SIZE);
        do {
            try {
                // 续费锁
                taskCache.renewalLock(index);

                urlDb.setPage(groupPage++);
                urls = urlMapper.select(urlDb);

                if (urls != null && urls.size() > 0) {
                    for (final JurisdictionUrlDb url : urls) {
                        urlCache.set("[" + url.getMethod().toUpperCase() + "]"
                                + url.getBaseUrl() + url.getUrl(), String.valueOf(url.getId()));
                    }
                }
            } catch (Exception e) {
                // 释放锁
                taskCache.releaseLock(index);
                e.printStackTrace();
                notification(e);
                break;
            }
        } while (urls != null && urls.size() == DB_SIZE);

        // 任务完成序号累加
        taskCache.indexIncr();
        // 释放锁
        taskCache.releaseLock(index);

        LOGGER.info("end run task [ DbToCacheTask ].");
    }

    /**
     * 异常通知
     * @param throwable 异常对象
     */
    private void notification(Throwable throwable) {
        final P6eNotification p6eNotification = P6eSpringUtil.getBean(P6eNotification.class, null);
        if (p6eNotification != null) {
            p6eNotification.error("[ "
                    + P6eSpringUtil.getProperty("spring.application.name", "P6E_JURISDICTION_DEFAULT")
                    + " ] JURISDICTION DB TO CACHE TASK ERROR", throwable);
        }
    }

}
