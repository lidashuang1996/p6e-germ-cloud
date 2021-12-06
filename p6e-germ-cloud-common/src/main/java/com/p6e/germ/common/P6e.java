package com.p6e.germ.common;

import com.p6e.germ.common.config.P6eCacheRedisConfig;
import com.p6e.germ.common.config.P6eConfig;
import com.p6e.germ.common.utils.P6eSpringUtil;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author lidashuang
 * @version 1.0
 */
public final class P6e {

    public static void init(final ApplicationContext context) {
        P6eSpringUtil.init(context);
        final P6eConfig config = P6eSpringUtil.getBean(P6eConfig.class);
        initCacheRedis(config.getCache().getRedis());
    }

    private static void initCacheRedis(P6eCacheRedisConfig config) {
        try {
            final Class<?> clazz = Class.forName("com.p6e.germ.cache.redis.P6eCacheRedisAbstract");
            final Method method = clazz.getMethod("setConfig", P6eCacheRedisConfig.class);
            method.invoke(null, config);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
