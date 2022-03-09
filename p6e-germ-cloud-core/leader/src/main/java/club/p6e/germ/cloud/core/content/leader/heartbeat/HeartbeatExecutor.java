package club.p6e.germ.cloud.core.content.leader.heartbeat;

import club.p6e.germ.cloud.core.content.leader.P6eCloudCoreLeaderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 心跳执行者
 * @author lidashuang
 * @version 1.0
 */
@Component
public class HeartbeatExecutor {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatExecutor.class);

    private final RestTemplate restTemplate = new RestTemplate();
    private final transient Map<String, HeartbeatModel> cache = new HashMap<>();

    @Resource
    private P6eCloudCoreLeaderProperties properties;

    public void run() {
        init(); // 初始化参数
        request(); // 请求数据
    }

    public Map<String, HeartbeatModel> get() {
        return cache;
    }

    private void init() {
        final P6eCloudCoreLeaderProperties.Heartbeat[] leaders = properties.getExecutor();
        for (final P6eCloudCoreLeaderProperties.Heartbeat leader : leaders) {
            cache.putIfAbsent(leader.getName(), new HeartbeatModel(
                    leader.getName(), leader.getIp(), leader.getPort(), "0", 0, 0, new HashMap<>()));
        }
    }

    @SuppressWarnings("all")
    private void request() {
        final List<Runnable> runnableList = new ArrayList<>();
        final P6eCloudCoreLeaderProperties.Heartbeat[] leaders = properties.getExecutor();
        for (final P6eCloudCoreLeaderProperties.Heartbeat leader : leaders) {
            runnableList.add(() -> {
                final HeartbeatModel model = cache.get(leader.getName());
                try {
                    final Object result = restTemplate.getForObject("http"
                            + "://"
                            + leader.getIp()
                            + ":"
                            + leader.getPort()
                            + "/heartbeat", Object.class);
                    LOGGER.info(result == null ? "[null]" : result.toString());
                    final Map<String, Object> map = (Map<String, Object>) result;
                    if (map != null && map.get("code") != null && map.get("code").equals("0")) {
                        model.setError(0);
                        model.setStatus("1");
                        model.setDate(System.currentTimeMillis());
                    } else {
                        model.setError(model.getError() + 1);
                        model.setDate(System.currentTimeMillis());
                        if (model.getError() > 3) {
                            model.setStatus("0");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.info(e.getMessage());
                    model.setError(model.getError() + 1);
                    model.setDate(System.currentTimeMillis());
                    if (model.getError() > 3) {
                        model.setStatus("0");
                    }
                }
            });
        }
        new HeartbeatThreadPool(runnableList).execute();
    }


}
