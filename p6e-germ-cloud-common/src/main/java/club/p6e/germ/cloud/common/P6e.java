package club.p6e.germ.cloud.common;

import club.p6e.germ.cloud.common.utils.P6eSpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;

/**
 * P6e 配置类
 * @author lidashuang
 * @version 1.0
 */
public final class P6e {

    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(P6e.class);

    /** Spring 的 ApplicationContext 对象 */
    private static ApplicationContext APPLICATION_CONTEXT;

    /**
     * 初始化
     * 如果已经初始化将不在进行初始化的操作
     * 注入 Spring 的 ApplicationContext 对象
     * 通过注入的 Spring 的 ApplicationContext 对象，初始化内置的服务
     * @param applicationContext Spring 的 ApplicationContext 对象
     */
    public static synchronized void init(@NonNull ApplicationContext applicationContext) {
        if (APPLICATION_CONTEXT == null) {
            LOGGER.info("p6e initialization started.");

            APPLICATION_CONTEXT = applicationContext;

            // 初始化 P6eSpringUtil
            LOGGER.info("[ " + P6eSpringUtil.class + " ] initialization started.");
            P6eSpringUtil.init(applicationContext);
            LOGGER.info("[ " + P6eSpringUtil.class + " ] initialization end.");

            LOGGER.info("p6e initialization end.");
        }
    }

    /**
     * 获取 Spring 的 ApplicationContext 对象
     * @return Spring 的 ApplicationContext 对象
     */
    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

}
