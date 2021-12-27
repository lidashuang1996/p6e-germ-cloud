package club.p6e.germ.cloud.jurisdiction.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author lidashuang
 * @version 1.0
 */
@Configuration
@EnableJpaRepositories(
        basePackages = JurisdictionDataSourceConfig.JURISDICTION_DATA_SOURCE_REPOSITORY_PACKAGES,
        entityManagerFactoryRef = JurisdictionDataSourceConfig.JURISDICTION_DATA_SOURCE_ENTITY_MANAGER_FACTORY_BEAN_NAME,
        transactionManagerRef = JurisdictionDataSourceConfig.JURISDICTION_DATA_SOURCE_TRANSACTION_MANAGER_BEAN_NAME)
@EnableTransactionManagement
public class JurisdictionDataSourceConfig {

    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(JurisdictionDataSourceConfig.class);

    /** 数据源的 BEAN 的名称 */
    public static final String JURISDICTION_DATA_SOURCE_BEAN_NAME = "JurisdictionDataSource";
    /** 模型包路径 */
    public static final String JURISDICTION_DATA_SOURCE_ENTITY_PACKAGES = "club.p6e.germ.cloud.jurisdiction.model";
    /** 服务包路径 */
    public static final String JURISDICTION_DATA_SOURCE_REPOSITORY_PACKAGES = "club.p6e.germ.cloud.jurisdiction.repository";

    /** 需要的 BEAN 注入的名称 */
    public static final String JURISDICTION_DATA_SOURCE_ENTITY_MANAGER_FACTORY_BEAN_NAME = "JurisdictionDataSourceEntityManagerFactoryBean";
    public static final String JURISDICTION_DATA_SOURCE_ENTITY_MANAGER_BEAN_NAME = "JurisdictionDataSourceEntityManager";
    public static final String JURISDICTION_DATA_SOURCE_TRANSACTION_MANAGER_BEAN_NAME = "JurisdictionDataSourceTransactionManager";

    /** 单元测试名称 */
    public static final String JURISDICTION_DATA_SOURCE_PERSISTENCE_UNIT_BEAN_NAME = "JurisdictionDataSourcePersistenceUnit";

    @Resource
    @Qualifier(JURISDICTION_DATA_SOURCE_BEAN_NAME)
    private DataSource dataSource;

    @Bean(name = JURISDICTION_DATA_SOURCE_ENTITY_MANAGER_FACTORY_BEAN_NAME)
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        LOGGER.info(JURISDICTION_DATA_SOURCE_ENTITY_MANAGER_FACTORY_BEAN_NAME + " ==> init LocalContainerEntityManagerFactoryBean.");
        return entityManagerFactoryBuilder.dataSource(dataSource)
                .packages(JURISDICTION_DATA_SOURCE_ENTITY_PACKAGES)
                .persistenceUnit(JURISDICTION_DATA_SOURCE_PERSISTENCE_UNIT_BEAN_NAME)
                .build();
    }

    @Bean(name = JURISDICTION_DATA_SOURCE_ENTITY_MANAGER_BEAN_NAME)
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        LOGGER.info(JURISDICTION_DATA_SOURCE_ENTITY_MANAGER_BEAN_NAME + " ==> init EntityManager.");
        return Objects.requireNonNull(entityManagerFactoryBean(builder).getObject()).createEntityManager();
    }

    @Bean(name = JURISDICTION_DATA_SOURCE_TRANSACTION_MANAGER_BEAN_NAME)
    public JpaTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        LOGGER.info(JURISDICTION_DATA_SOURCE_TRANSACTION_MANAGER_BEAN_NAME + " ==> init JpaTransactionManager.");
        final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean(builder).getObject());
        return jpaTransactionManager;
    }

}