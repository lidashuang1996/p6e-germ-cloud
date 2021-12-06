package club.p6e.germ.cloud.auth;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author lidashuang
 * @version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ServletComponentScan
@EnableAutoConfiguration
@Import(P6eAuthAutoConfigurationImportSelector.class)
public @interface P6eAuthApplication {
}
