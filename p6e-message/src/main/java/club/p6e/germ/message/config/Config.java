package club.p6e.germ.message.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 配置文件对象
 * @author lidashuang
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix = "p6e.message")
public class Config {

    /** 控制台 */
    private Console console = new Console();
    /** 上下文 */
    private Context context = new Context();
    /** 数据库 */
    private DataSource dataSource = new DataSource();

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 控制台
     */
    public static class Console {
        /** 白名单 IP 配置 */
        private String ip = "0.0.0.0";

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }

    /**
     * 上下文
     */
    public static class Context {
        /** 白名单 IP 配置 */
        private String ip = "0.0.0.0";
        /** 每次处理最大长度的收件人 */
        private int maxLengthRecipient = 20;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getMaxLengthRecipient() {
            return maxLengthRecipient;
        }

        public void setMaxLengthRecipient(int maxLengthRecipient) {
            this.maxLengthRecipient = maxLengthRecipient;
        }
    }

    /**
     * 数据库对象
     */
    public static class DataSource {
        /** 默认的查询页码配置 */
        private int defaultPage = 1;
        /** 默认的查询长度配置 */
        private int defaultSize = 16;
        /** 默认的查询最大长度配置 */
        private int defaultMaxSize = 300;

        private String url;
        private String username;
        private String password;
        private String driverClassName;

        /** 是否自动提交 */
        private boolean autoCommit = true;
        /** 连接建立超时时间 单位ms 默认30000 */
        private long connectionTimeout = 30000;

        /**
         * 空闲连接超时时间 单位ms 最小10000(10s) 默认600000(10min)
         * 当 minimumIdle 小于 maximumPoolSize 时有效
         * 0 空闲连接永远不会被移除
         */
        private long idleTimeout = 30000;

        /**
         * 最大连接数（包含空闲和正在使用的连接），默认值10.
         * 当连接数达到该值时，新的连接申请会被阻塞直到超时。
         */
        private int maximumPoolSize = 10;

        /**
         * 最小空闲连接数，默认值 maximumPoolSize。
         * 当空闲连接低于该值且总连接数低于maximumPoolSize时，HikariCP会立即添加连接来保证一个高效的性能
         * 但是，HikariCP 不建议配置该值，建议使用默认值，让HikariCP维护一个固定连接数的连接池来保持高效。
         */
        private int minimumIdle = maximumPoolSize;

        /**
         * 一个连接在连接池中的最长存活时间，正在被使用的链接如果超时会等到关闭后被移除
         * 最小30000ms (30 seconds). Default: 1800000 (30 minutes)
         * 0 连接永不超时
         */
        private long maxLifetime = 1800000;

        /**
         * 连接测试查询语句，如果驱动支持JDBC4，强烈建议不配置该属性
         * 尝试不配置该属性来运行连接池，如果驱动不支持JDBC4，HikariCP会打出一个错误日志通知你。
         * 默认值：none
         */
        private String connectionTestQuery = null;

        /**
         * 指标记录，默认：none。
         * 这个属性只适用于程序配置或ioc容器，允许你指定一个 Codahale/Dropwizard MetricRegistry 来记录指标。
         * 参考：https://github.com/brettwooldridge/HikariCP/wiki/Dropwizard-Metrics
         */
        private Properties metricRegistry = null;

        /**
         * 健康检查，默认：none。允许你指定一个 Codahale/Dropwizard HealthCheckRegistry 来报告当前健康信息。
         * 参考 https://github.com/brettwooldridge/HikariCP/wiki/Dropwizard-HealthChecks
         */
        private Properties healthCheckRegistry = null;

        /** 连接池名称 */
        private String poolName = "hikariPool";

        public int getDefaultPage() {
            return defaultPage;
        }

        public void setDefaultPage(int defaultPage) {
            this.defaultPage = defaultPage;
        }

        public int getDefaultSize() {
            return defaultSize;
        }

        public void setDefaultSize(int defaultSize) {
            this.defaultSize = defaultSize;
        }

        public int getDefaultMaxSize() {
            return defaultMaxSize;
        }

        public void setDefaultMaxSize(int defaultMaxSize) {
            this.defaultMaxSize = defaultMaxSize;
        }

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

        public boolean isAutoCommit() {
            return autoCommit;
        }

        public void setAutoCommit(boolean autoCommit) {
            this.autoCommit = autoCommit;
        }

        public long getConnectionTimeout() {
            return connectionTimeout;
        }

        public void setConnectionTimeout(long connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
        }

        public long getIdleTimeout() {
            return idleTimeout;
        }

        public void setIdleTimeout(long idleTimeout) {
            this.idleTimeout = idleTimeout;
        }

        public int getMaximumPoolSize() {
            return maximumPoolSize;
        }

        public void setMaximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
        }

        public int getMinimumIdle() {
            return minimumIdle;
        }

        public void setMinimumIdle(int minimumIdle) {
            this.minimumIdle = minimumIdle;
        }

        public long getMaxLifetime() {
            return maxLifetime;
        }

        public void setMaxLifetime(long maxLifetime) {
            this.maxLifetime = maxLifetime;
        }

        public String getConnectionTestQuery() {
            return connectionTestQuery;
        }

        public void setConnectionTestQuery(String connectionTestQuery) {
            this.connectionTestQuery = connectionTestQuery;
        }

        public Properties getMetricRegistry() {
            return metricRegistry;
        }

        public void setMetricRegistry(Properties metricRegistry) {
            this.metricRegistry = metricRegistry;
        }

        public Properties getHealthCheckRegistry() {
            return healthCheckRegistry;
        }

        public void setHealthCheckRegistry(Properties healthCheckRegistry) {
            this.healthCheckRegistry = healthCheckRegistry;
        }

        public String getPoolName() {
            return poolName;
        }

        public void setPoolName(String poolName) {
            this.poolName = poolName;
        }
    }

}
