package club.p6e.germ.cloud.core.content.leader;

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
@ConfigurationProperties(prefix = "p6e.cloud.core.leader")
public class P6eCloudCoreLeaderProperties implements Serializable {

    private int interval = 3000;

    private int weight = 100;
//    private String type = "master"; // master follow
    private Heartbeat[] leaders = new Heartbeat[] {
            new Heartbeat("l1", "127.0.0.1", "28100", 90),
//            new Heartbeat("l2", "127.0.0.1", "28200")
    };
    private Heartbeat[] executor = new Heartbeat[] {
            new Heartbeat("l1", "127.0.0.1", "28100", 90),
//            new Heartbeat("l2", "127.0.0.1", "28200")
    };


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Heartbeat implements Serializable {
        private String name;
        private String ip = "127.0.0.1";
        private String port = "28000";
        private int weight = 100;
    }

}
