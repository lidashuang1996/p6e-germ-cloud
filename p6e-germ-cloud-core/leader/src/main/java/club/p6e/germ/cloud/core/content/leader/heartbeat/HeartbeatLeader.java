package club.p6e.germ.cloud.core.content.leader.heartbeat;

import club.p6e.germ.cloud.core.content.leader.P6eCloudCoreLeaderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * 心跳领导者
 * @author lidashuang
 * @version 1.0
 */
@Component
public class HeartbeatLeader {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartbeatLeader.class);

    private final RestTemplate restTemplate = new RestTemplate();
    private final transient Map<String, HeartbeatModel> cache = new HashMap<>();

    private String type;

    @Resource
    private P6eCloudCoreLeaderProperties properties;

    public void run() {
        init(); // 初始化参数
        request(); // 请求数据
    }

    public String getType() {
        return type;
    }

    public Map<String, HeartbeatModel> get() {
        return cache;
    }

    private void init() {
        final P6eCloudCoreLeaderProperties.Heartbeat[] leaders = properties.getLeaders();
        for (final P6eCloudCoreLeaderProperties.Heartbeat leader : leaders) {
            final Map<String, Object> attributes = new HashMap<>();
            attributes.put("weight", leader.getWeight());
            cache.putIfAbsent(leader.getName(), new HeartbeatModel(
                    leader.getName(), leader.getIp(), leader.getPort(), "0", 0, 0, attributes));
        }
    }

    @SuppressWarnings("all")
    private void request() {
        final List<Runnable> runnableList = new ArrayList<>();
        final P6eCloudCoreLeaderProperties.Heartbeat[] leaders = properties.getLeaders();
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
                        // 是否重新连接
                        final boolean bool = model.getError() > 3;
                        model.setError(0);
                        model.setStatus("1");
                        model.setDate(System.currentTimeMillis());
                        if (bool) {
                            // 请求重新分配
                            requestRedistribution();
                        }
                    } else {
                        model.setError(model.getError() + 1);
                        model.setDate(System.currentTimeMillis());
                        if (model.getError() > 3) {
                            model.setStatus("0");
                            // 请求重新分配
                            requestRedistribution();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.info(e.getMessage());
                    model.setError(model.getError() + 1);
                    model.setDate(System.currentTimeMillis());
                    if (model.getError() > 3) {
                        model.setStatus("0");
                        // 请求重新分配
                        requestRedistribution();
                    }
                }
            });
        }
        new HeartbeatThreadPool(runnableList).execute();
    }

    /**
     * 重新分配主从节点信息
     */
    private void requestRedistribution() {
        HeartbeatModel heartbeatModel = null;
        final int weight = properties.getWeight();
        for (final HeartbeatModel model : get().values()) {
            if (model.getError() <= 3) {
                final int modelWeight = Double.valueOf(model.getAttributes().get("weight").toString()).intValue();
                if (modelWeight > weight) {
                    heartbeatModel = model;
                }
            }
        }
        if (heartbeatModel == null) {
            executeRedistribution();
        } else {
            // 调用远端
            LOGGER.info("redistribution ==> " + (Objects.requireNonNull(restTemplate.getForObject(
                    "http" + "://" + heartbeatModel.getIp() + ":" + heartbeatModel.getPort() + "/heartbeat", Object.class))));
        }
    }

    /**
     * 执行分配
     */
    private void executeRedistribution() {

    }

}
