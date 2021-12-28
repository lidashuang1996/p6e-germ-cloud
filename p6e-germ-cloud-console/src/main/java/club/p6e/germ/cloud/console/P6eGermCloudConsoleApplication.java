package club.p6e.germ.cloud.console;

import com.p6e.germ.common.P6e;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class P6eGermCloudConsoleApplication {

    public static void main(String[] args) {
        P6e.init(
                SpringApplication.run(P6eGermCloudConsoleApplication.class, args)
        );
    }

}
