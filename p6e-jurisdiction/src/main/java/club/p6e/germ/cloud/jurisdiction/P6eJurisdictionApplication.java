package club.p6e.germ.cloud.jurisdiction;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author lidashuang
 * @version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableAutoConfiguration
@Import({P6eJurisdictionAutoConfigurationImportSelector.class})
public @interface P6eJurisdictionApplication {
}
