package club.p6e.germ.cloud.gateway.test;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.p6e.germ.common.config.P6eConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RefreshScope
@RequestMapping("/test")
public class TestController {

    @Resource
    private P6eConfig config;

    @Value("${my.name}")
    private String name;

    @RequestMapping
    public Mono<Object> def() {
        try {
            System.out.println(config.getCache().getRedis().getClientName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(name + "xxxxxx" + config);
    }

}
