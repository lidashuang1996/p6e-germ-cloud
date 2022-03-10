package club.p6e.germ.cloud.gateway;

import club.p6e.germ.cloud.common.P6e;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关应用
 * 1. 自定义 CORS
 * 2. 权限验证
 * 3. 用户信息写入
 * 4. 应用路由
 * 5. 日志
 * 6. 异常
 * @author lidashuang
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class P6eGermCloudGatewayApplication {

    /** 是否为 DEBUG 状态 */
    public static final boolean IS_DEBUG = true;

    public static void main(String[] args) {
        P6e.init(
                SpringApplication.run(P6eGermCloudGatewayApplication.class, args)
        );
    }

}
