package club.p6e.germ.cloud.core.content.leader.controller;

import club.p6e.germ.cloud.core.content.leader.heartbeat.HeartbeatLeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping(value = "")
public class DefaultController {

    @Resource
    private HeartbeatLeader heartbeatLeader;

    @RequestMapping(value = "/heartbeat", method = { RequestMethod.POST, RequestMethod.GET })
    public Map<String, Object> heartbeat() {
        final Map<String, Object> result = new HashMap<>();
        result.put("code", "0");
        result.put("data", heartbeatLeader.get());
        return result;
    }

    @RequestMapping(value = "/heartbeat/redistribution", method = { RequestMethod.POST, RequestMethod.GET })
    public Map<String, Object> heartbeatRedistribution() {
        final Map<String, Object> result = new HashMap<>();
        result.put("code", "0");
        result.put("data", heartbeatLeader.get());
        return result;
    }

}
