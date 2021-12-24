package club.p6e.germ.cloud.jurisdiction;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
@ConfigurationProperties(
        prefix = "p6e.cloud.jurisdiction"
)
public class P6eCloudJurisdictionProperties {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static class DataSource {
        private String url;
        private String username;
        private String password;
        private String driverClassName;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        @Override
        public String toString() {
            return "{"
                    + "\"url\":\""
                    + url + '\"'
                    + ",\"username\":\""
                    + username + '\"'
                    + ",\"password\":\""
                    + password + '\"'
                    + ",\"driverClassName\":\""
                    + driverClassName + '\"'
                    + "}";
        }
    }

}
