package club.p6e.germ.cloud.core.content.leader.heartbeat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 心跳请求的线程池
 * @author lidashuang
 * @version 1.0
 */
public final class HeartbeatThreadPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatThreadPool.class);

    private static ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(5,
            Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());

    private final transient List<Runnable> runnableList;
    private final transient CountDownLatch countDownLatch;

    public static void setExecutor(ThreadPoolExecutor threadPool) {
        if (HeartbeatThreadPool.THREAD_POOL != null) {
            HeartbeatThreadPool.THREAD_POOL.shutdown();
        }
        HeartbeatThreadPool.THREAD_POOL = threadPool;
    }

    public HeartbeatThreadPool(List<Runnable> list) {
        if (list != null && list.size() > 0) {
            this.runnableList = list;
            this.countDownLatch = new CountDownLatch(list.size());
        } else {
            throw new RuntimeException(this.getClass() + " list is null or size is 0.");
        }
    }

    public void execute() {
        try {
            for (final Runnable runnable : runnableList) {
                THREAD_POOL.execute(() -> {
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            }
            countDownLatch.await();
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }

}
