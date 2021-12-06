package club.p6e.germ.cloud.auth.infrastructure.cache.support;

import com.p6e.germ.cache.redis.P6eCacheRedisAbstract;
import com.p6e.germ.common.P6e;
import com.p6e.germ.common.config.P6eConfig;
import com.p6e.germ.common.utils.P6eSpringUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class TestRedisCache extends P6eCacheRedisAbstract implements ApplicationRunner {


    /** 注入 ApplicationContext 对象 */
    @Resource
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            P6e.init(applicationContext);
            setConfig(P6eSpringUtil.getBean(P6eConfig.class).getCache().getRedis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
