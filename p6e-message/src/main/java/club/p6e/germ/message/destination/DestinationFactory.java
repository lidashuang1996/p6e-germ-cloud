package club.p6e.germ.message.destination;

import com.p6e.germ.common.utils.P6eSpringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 终点工厂
 * @author lidashuang
 * @version 1.0
 */
public abstract class DestinationFactory {
    /** 线程池 */
    private static ThreadPoolExecutor EXECUTOR;
    /** 默认名称 */
    private static final String DEFAULT_NAME = "default";
    private static final String SYSTEM_DEFAULT_NAME = "__default__";
    /** 短信缓存 */
    private static final Map<String, DestinationInterface> SMS = new HashMap<>();
    /** 邮件缓存 */
    private static final Map<String, DestinationInterface> MAIL = new HashMap<>();

    /** 初始化一次 */
    public synchronized static void load() {
        SMS.clear();
        MAIL.clear();
        final Map<String, DestinationInterface> map = P6eSpringUtil.getBeans(DestinationInterface.class);
        for (final DestinationInterface destination : map.values()) {
            final String type = destination.type();
            final String name = destination.name();
            if (DestinationInterface.SMS_TYPE.equals(type)) {
                SMS.put(name, destination);
            } else if (DestinationInterface.MAIL_TYPE.equals(type)) {
                MAIL.put(name, destination);
            }
        }
    }

    /**
     * 初始化线程池
     */
    public static void initExecutor() {
        initExecutor(new ThreadPoolExecutor(5, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new DefaultThreadFactory()));
    }

    /**
     * 初始化线程池
     * @param executor 线程池对象
     */
    public synchronized static void initExecutor(ThreadPoolExecutor executor) {
        if (EXECUTOR == null) {
            EXECUTOR = executor;
        }
    }

    /**
     * 关闭线程池
     */
    public synchronized static void shutdownExecutor() {
        if (EXECUTOR != null) {
            EXECUTOR.shutdown();
            EXECUTOR = null;
        }
    }

    /**
     * 短信
     * @param name 名称
     * @return 短信处理器
     */
    public static DestinationInterface sms(String name) {
        return SMS.get(name) == null ? SMS.get(DEFAULT_NAME) == null ?
                SMS.get(SYSTEM_DEFAULT_NAME) : SMS.get(DEFAULT_NAME) : SMS.get(name);
    }

    /**
     * 邮件
     * @param name 名称
     * @return 邮件处理器
     */
    public static DestinationInterface mail(String name) {
        return MAIL.get(name) == null ? MAIL.get(DEFAULT_NAME) == null ?
                MAIL.get(SYSTEM_DEFAULT_NAME) : MAIL.get(DEFAULT_NAME) : MAIL.get(name);
    }

    /**
     * 执行任务
     */
    public static void task(Runnable runnable) {
        if (EXECUTOR == null) {
            throw new NullPointerException(DestinationFactory.class + ", task thread pool executor is null.");
        } else {
            EXECUTOR.execute(runnable);
        }
    }

    /**
     * 线程池
     */
    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "launcher-" +
                    POOL_NUMBER.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
