package club.p6e.germ.cloud.core.content.config;

import club.p6e.germ.cloud.core.content.P6eCloudCoreContentProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Objects;


/**
 * @version 1.0
 */
@Configuration
@EnableJpaRepositories(
        basePackages = DataSourceConfig.REPOSITORY_PACKAGES,
        entityManagerFactoryRef = DataSourceConfig.SOURCE + "DataSourceEntityManagerFactoryBean",
        transactionManagerRef = DataSourceConfig.SOURCE + "DataSourceTransactionManager")
@EnableTransactionManagement
public class DataSourceConfig {

    /** 数据源 */
    public static final String SOURCE = "P6eCore";
    public static final String MODEL_PACKAGES = "club.p6e.germ.cloud.core.content.repository.model";
    public static final String REPOSITORY_PACKAGES = "club.p6e.germ.cloud.core.content.repository";
    /** 注入日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Primary
    @Bean(name = SOURCE + "DataSource")
    public DataSource dbDataSource(P6eCloudCoreContentProperties properties) {
        final P6eCloudCoreContentProperties.DataSource dataSource = properties.getDataSource();
        LOGGER.info(SOURCE + "DataSource ==> " + dataSource);
        return DataSourceBuilder.create()
                .url(dataSource.getUrl())
                .username(dataSource.getUsername())
                .password(dataSource.getPassword())
                .driverClassName(dataSource.getDriverClassName())
                .build();
    }

    @Primary
    @Bean(name = SOURCE + "DataSourceEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            @Qualifier(SOURCE + "DataSource") DataSource dataSource, EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        LOGGER.info(SOURCE + "DataSource ==> init LocalContainerEntityManagerFactoryBean.");
        return entityManagerFactoryBuilder.dataSource(dataSource)
                .packages(MODEL_PACKAGES)
                .persistenceUnit(SOURCE + "DataSourcePersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = SOURCE + "DataSourceEntityManager")
    public EntityManager entityManager(@Qualifier(SOURCE + "DataSourceEntityManagerFactoryBean")
                                                   LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        LOGGER.info(SOURCE + "DataSource ==> init EntityManager.");
        return Objects.requireNonNull(entityManagerFactoryBean.getObject()).createEntityManager();
    }

    @Primary
    @Bean(name = SOURCE + "DataSourceTransactionManager")
    public JpaTransactionManager transactionManager(@Qualifier(SOURCE + "DataSourceEntityManagerFactoryBean")
                                                                LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        LOGGER.info(SOURCE + "DataSource ==> init JpaTransactionManager.");
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return jpaTransactionManager;
    }

}
