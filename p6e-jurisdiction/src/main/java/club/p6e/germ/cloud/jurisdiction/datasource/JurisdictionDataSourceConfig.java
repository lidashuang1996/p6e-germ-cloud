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
 * @version 1.0
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "club.p6e.germ.cloud.jurisdiction.repository",
        entityManagerFactoryRef = "JurisdictionDataSourceEntityManagerFactoryBean",
        transactionManagerRef = "JurisdictionDataSourceTransactionManager")
@EnableTransactionManagement
public class JurisdictionDataSourceConfig {

    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(JurisdictionDataSourceConfig.class);

    @Resource
    @Qualifier("JurisdictionDataSource")
    private DataSource dataSource;

    @Bean(name = "JurisdictionDataSourceEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        LOGGER.info("jurisdiction source ==> init LocalContainerEntityManagerFactoryBean.");
        return entityManagerFactoryBuilder.dataSource(dataSource)
                .packages("club.p6e.germ.cloud.jurisdiction.model")
                .persistenceUnit("JurisdictionDataSourcePersistenceUnit")
                .build();
    }

    @Bean(name = "JurisdictionDataSourceEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        LOGGER.info("jurisdiction source ==> init EntityManager.");
        return Objects.requireNonNull(entityManagerFactoryBean(builder).getObject()).createEntityManager();
    }

    @Bean(name = "JurisdictionDataSourceTransactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        LOGGER.info("jurisdiction source ==> init JpaTransactionManager.");
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactoryBean(builder).getObject());
        return jpaTransactionManager;
    }

}