package club.p6e.germ.cloud.jurisdiction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lidashuang
 * @version 1.0
 */
public class P6eJurisdictionAutoConfigurationImportSelector implements ImportBeanDefinitionRegistrar {

    /** 默认扫描的包 */
    private static final String DEFAULT_SCAN_PACKAGE = "club.p6e.germ.cloud.jurisdiction";
    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(P6eJurisdictionAutoConfigurationImportSelector.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        final GenericApplicationContext context = new GenericApplicationContext();
        final ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(context, true);
        scanner.scan(DEFAULT_SCAN_PACKAGE);
        for (final String beanDefinitionName : context.getBeanDefinitionNames()) {
            if (!registry.isBeanNameInUse(beanDefinitionName)) {
                context.getBeanDefinition(beanDefinitionName).getConstructorArgumentValues();
                LOGGER.info("registerBeanDefinition --> " + beanDefinitionName
                        + context.getBeanDefinition(beanDefinitionName).getBeanClassName());
                registry.registerBeanDefinition(beanDefinitionName, context.getBeanDefinition(beanDefinitionName));
            }
        }
    }

}
