package club.p6e.germ.cloud.jurisdiction.datasource;

import club.p6e.germ.cloud.jurisdiction.P6eCloudJurisdictionProperties;
import com.p6e.germ.common.utils.P6eJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * @version 1.0
 */
@Configuration
public class DataSourceConfig {

    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean(name = "JurisdictionDataSource")
    public DataSource dbDataSource2(P6eCloudJurisdictionProperties properties) {
        final P6eCloudJurisdictionProperties.DataSource dataSourceConfig = properties.getDataSource();
        LOGGER.info("Jurisdiction data source ==> " + dataSourceConfig);
        return DataSourceBuilder.create()
                .url(dataSourceConfig.getUrl())
                .username(dataSourceConfig.getUsername())
                .password(dataSourceConfig.getPassword())
                .driverClassName(dataSourceConfig.getDriverClassName())
                .build();
    }
}
