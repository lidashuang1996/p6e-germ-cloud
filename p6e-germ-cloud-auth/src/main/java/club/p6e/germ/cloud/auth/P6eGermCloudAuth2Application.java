package club.p6e.germ.cloud.auth;

import com.p6e.germ.common.P6e;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lidashuang
 * @version 1.0
 */
@P6eAuthApplication
@SpringBootApplication
public class P6eGermCloudAuth2Application {

    public static void main(String[] args) {
        P6e.init(SpringApplication.run(P6eGermCloudAuth2Application.class, args));
        System.out.println();
    }

}
