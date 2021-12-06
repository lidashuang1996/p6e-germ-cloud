package club.p6e.germ.message;

import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

/**
 * @author lidashuang
 * @version 1.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ EnableP6eMessageImportSelector.class })
public @interface EnableP6eMessage {
}
