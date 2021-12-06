package club.p6e.germ.jurisdiction;

import com.p6e.germ.common.P6e;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		P6e.init(
				SpringApplication.run(DemoApplication.class, args)
		);
	}

}
