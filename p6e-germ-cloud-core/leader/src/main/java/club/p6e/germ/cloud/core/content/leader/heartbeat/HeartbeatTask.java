package club.p6e.germ.cloud.core.content.leader.heartbeat;


import club.p6e.germ.cloud.core.content.leader.P6eCloudCoreLeaderProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

/**
 * 心跳任务
 * @author lidashuang
 * @version 1.0
 */
@Configuration
@EnableScheduling
public class HeartbeatTask {

    private int count = 0;

    @Resource
    private HeartbeatLeader heartbeatLeader;

    @Resource
    private HeartbeatExecutor heartbeatExecutor;

    @Resource
    private P6eCloudCoreLeaderProperties properties;

    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    private void def() {
        count ++;
        final int interval = properties.getInterval();
        if (count * 1000 >= interval) {
            count = 0;
            heartbeatLeader.run();
            heartbeatExecutor.run();
        }
    }

}
