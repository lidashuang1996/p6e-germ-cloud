package club.p6e.germ.cloud.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * P6e Spring 配置类
 * @author lidashuang
 * @version 1.0
 */
public final class P6eSpringUtil {

    /** Spring 的 ApplicationContext 对象 */
    private static ApplicationContext APPLICATION;

    /**
     * Spring 的 ApplicationContext 对象
     * @param applicationContext Spring 的 ApplicationContext 对象
     */
    public static synchronized void init(@NonNull ApplicationContext applicationContext) {
        if (APPLICATION == null) {
            APPLICATION = applicationContext;
        }
    }

    /**
     * 检查 Spring 的 ApplicationContext 对象
     */
    private static void checkApplicationContext() {
        if (APPLICATION == null) {
            throw new NullPointerException(P6eSpringUtil.class + " APPLICATION is null -> " +
                    "[ please check whether the club.p6e.germ.cloud.common.P6e.init() method is executed. ].");
        }
    }

    /**
     * 获取 Spring 管理的 Java Bean 对象
     * @param <T> Java Bean 的类型
     * @param beanClass Java Bean 的 Class
     * @return 获取的 Spring 管理的 Java Bean 对象
     */
    public static <T> T getBean(@NonNull Class<T> beanClass) {
        checkApplicationContext();
        return APPLICATION.getBean(beanClass);
    }

    /**
     * 获取 Spring 管理的 Java Bean 对象，可设置一个默认 Java 对象的回调函数
     * @param <T> Java Bean 的类型
     * @param beanClass Java Bean 的 Class
     * @param beanCallback Java 对象的回调函数
     * @return 获取的 JAVA Bean 对象
     */
    public static <T> T getBean(@NonNull Class<T> beanClass, @NonNull BeanCallback<? extends T> beanCallback) {
        checkApplicationContext();
        try {
            return APPLICATION.getBean(beanClass);
        } catch (BeansException e) {
            return beanCallback.execute();
        }
    }

    /**
     * 获取 Spring 管理的 Java Bean 多个对象
     * @param <T> Java Bean 的类型
     * @param beanClass Java Bean 的 Class
     * @return key-value 形式的 多个 Java Bean 对象
     */
    public static <T> Map<String, T> getBeans(@NonNull Class<T> beanClass) {
        checkApplicationContext();
        return APPLICATION.getBeansOfType(beanClass);
    }

    /**
     * 读取配置文件的属性
     * @param key 属性名称
     * @return 读取的属性值
     */
    public static String getProperty(String key) {
        return getProperty(key, null);
    }

    /**
     * 读取配置文件的属性
     * @param key 属性名称
     * @param defaultValue 默认的属性值
     * @return 读取的属性值
     */
    public static String getProperty(String key, String defaultValue) {
        checkApplicationContext();
        final String r = APPLICATION.getEnvironment().getProperty(key);
        return r == null ? defaultValue : r;
    }

    /**
     * 默认 Java 对象回调函数
     */
    public interface BeanCallback<T> {

        /**
         * 返回 Java 对象
         * @return Java 对象
         */
        public @NonNull T execute();
    }
}
