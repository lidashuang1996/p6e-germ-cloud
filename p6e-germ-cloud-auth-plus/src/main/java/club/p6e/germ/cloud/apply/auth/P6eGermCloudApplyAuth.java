package club.p6e.germ.cloud.apply.auth;

import club.p6e.germ.cloud.auth.P6eAuthApplication;
import com.p6e.germ.common.P6e;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用认证系统
 * @author lidashuang
 * @version 1.0
 */
@P6eAuthApplication
@SpringBootApplication
public class P6eGermCloudApplyAuth {

    public static void main(String[] args) {
        P6e.init(SpringApplication.run(P6eGermCloudApplyAuth.class, args));
    }

}
