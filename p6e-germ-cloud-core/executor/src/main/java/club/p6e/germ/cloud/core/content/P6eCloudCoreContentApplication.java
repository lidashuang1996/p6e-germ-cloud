package club.p6e.germ.cloud.core.content;

import club.p6e.germ.cloud.core.content.program.Program;
import club.p6e.germ.cloud.core.content.service.LivePluginService;
import club.p6e.germ.cloud.core.content.service.impl.LiveRoomCacheServiceImpl;
import club.p6e.live.room.P6eLiveRoomApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
public class P6eCloudCoreContentApplication {

    /** 注入日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(P6eCloudCoreContentApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(P6eCloudCoreContentApplication.class, args);
    }

    /**
     * 初始化回调
     */
    @Bean
    public ApplicationRunner runner(final ApplicationContext application) {
        return args -> {
            final LiveRoomCacheServiceImpl roomCache = application.getBean(LiveRoomCacheServiceImpl.class);
            final Map<String, LivePluginService> plugins = application.getBeansOfType(LivePluginService.class);
            final P6eCloudCoreContentProperties properties = application.getBean(P6eCloudCoreContentProperties.class);
            Program.init(plugins.values());
            P6eLiveRoomApplication.init();
            roomCache.init(String.valueOf(properties.getNode().getIndex()));
            LOGGER.info("initialization completed ...");
            LOGGER.info(properties.toString());
        };
    }
}
