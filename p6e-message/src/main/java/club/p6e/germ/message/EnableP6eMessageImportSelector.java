package club.p6e.germ.message;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import javax.servlet.annotation.WebFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author lidashuang
 * @version 1.0
 */
public class EnableP6eMessageImportSelector implements ImportSelector {

    /** 扫描的包路径 */
    private static final String BASE_PACKAGE = "club.p6e.germ.message";

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        final List<String> result = new ArrayList<>();
        final ClassPathScanningCandidateComponentProvider
                scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(WebFilter.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(Component.class));
        scanner.addExcludeFilter(new AnnotationTypeFilter(SpringBootApplication.class));
        final Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(BASE_PACKAGE);
        for (final BeanDefinition candidateComponent : candidateComponents) {
            result.add(candidateComponent.getBeanClassName());
        }
        return result.toArray(new String[0]);
    }

}
