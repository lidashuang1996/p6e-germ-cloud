package club.p6e.germ.cloud.core.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "p6e.cloud.core.content")
public class P6eCloudCoreContentProperties implements Serializable {

    /**
     * 其他节点信息
     */
    private Node[] nodes = new Node[] {};

    /**
     * 本节点信息
     */
    private Node node = new Node("node", "127.0.0.1", 31000, 1);

    /**
     * 数据源信息
     */
    private DataSource dataSource = new DataSource(
            "jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&useSSL=false&characterEncoding=UTF-8",
            "root",
            "123456",
            "com.mysql.cj.jdbc.Driver"
    );

    /**
     * 节点对象模型
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Node {
        private String name;
        private String ip;
        private Integer port;
        private Integer index;
    }

    /**
     * 数据源对象模型
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataSource {
        private String url;
        private String username;
        private String password;
        private String driverClassName;
    }
}
