package club.p6e.germ.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author lidashuang
 * @version 1.0
 */
@EnableP6eMessage
@SpringBootApplication
@ServletComponentScan
public class P6eMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(P6eMessageApplication.class, args);
	}

}
