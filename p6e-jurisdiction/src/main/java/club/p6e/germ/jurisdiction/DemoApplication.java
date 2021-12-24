package club.p6e.germ.jurisdiction;

import club.p6e.germ.jurisdiction.repository.JurisdictionUrlGroupRepository;
import club.p6e.germ.jurisdiction.repository.JurisdictionUserRelationUrlGroupRepository;
import com.p6e.germ.common.P6e;
import com.p6e.germ.common.utils.P6eSpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		P6e.init(
				SpringApplication.run(DemoApplication.class, args)
		);

		System.out.println(P6eSpringUtil.getBean(JurisdictionUserRelationUrlGroupRepository.class).findAll());
		System.out.println(P6eSpringUtil.getBean(JurisdictionUrlGroupRepository.class).findAll());
	}

}
