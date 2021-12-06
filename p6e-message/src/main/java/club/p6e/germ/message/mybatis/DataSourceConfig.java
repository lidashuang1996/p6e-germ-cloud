package club.p6e.germ.message.mybatis;

import club.p6e.germ.message.config.Config;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author lidashuang
 * @version 1.0
 */
@Configuration
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = DataSourceConfig.HEAD + "SqlSessionFactory")
public class DataSourceConfig {

    /** 数据源标识 */
    public static final String HEAD = "P6eMessage";

    /** mapper.java 包路径 */
    public static final String PACKAGE = "club.p6e.germ.message.mybatis.mapper";

    /** mapper.xml 文件的相对路径 */
    public static final String MAPPER_LOCATION = "classpath:mappers/message/*.xml";

    @Bean(DataSourceConfig.HEAD + "DataSource")
    public DataSource createDateSourceBean(Config config) {
        final Config.DataSource dataSource = config.getDataSource();
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(dataSource.getUrl());
        hikariDataSource.setUsername(dataSource.getUsername());
        hikariDataSource.setPassword(dataSource.getPassword());
        hikariDataSource.setDriverClassName(dataSource.getDriverClassName());
        hikariDataSource.setAutoCommit(dataSource.isAutoCommit());
        hikariDataSource.setConnectionTimeout(dataSource.getConnectionTimeout());
        hikariDataSource.setMinimumIdle(dataSource.getMinimumIdle());
        hikariDataSource.setMaximumPoolSize(dataSource.getMaximumPoolSize());
        if (dataSource.getMinimumIdle() != dataSource.getMaximumPoolSize()) {
            hikariDataSource.setIdleTimeout(dataSource.getIdleTimeout());
        }
        hikariDataSource.setMaxLifetime(dataSource.getMaxLifetime());
        if (dataSource.getConnectionTestQuery() != null) {
            hikariDataSource.setConnectionTestQuery(dataSource.getConnectionTestQuery());
        }
        if (dataSource.getMetricRegistry() != null) {
            hikariDataSource.setMetricRegistry(dataSource.getMetricRegistry());
        }
        if (dataSource.getHealthCheckRegistry() != null) {
            hikariDataSource.setHealthCheckProperties(dataSource.getHealthCheckRegistry());
        }
        hikariDataSource.setPoolName(dataSource.getPoolName());
        return hikariDataSource;
    }

    @Bean(DataSourceConfig.HEAD + "TransactionManager")
    public DataSourceTransactionManager createTransactionManagerBean(
            @Qualifier(DataSourceConfig.HEAD + "DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(DataSourceConfig.HEAD + "SqlSessionFactory")
    public SqlSessionFactory createSqlSessionFactoryBean(
            @Qualifier(DataSourceConfig.HEAD + "DataSource") DataSource dataSource,
            @Qualifier(DataSourceConfig.HEAD + "PagingPlugin") PagingPlugin pagingPlugin) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setPlugins(pagingPlugin);
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(DataSourceConfig.HEAD + "SqlSessionTemplate")
    public SqlSessionTemplate createSqlSessionTemplateBean(
            @Qualifier(DataSourceConfig.HEAD + "SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
