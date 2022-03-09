package club.p6e.germ.cloud.core.content.leader.heartbeat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 心跳数据模型
 * @author lidashuang
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeartbeatModel implements Serializable {
    private String name;
    private String ip;
    private String port;
    private String status;
    private long date;
    private int error;
    private Map<String, Object> attributes;
}
