package club.p6e.germ.cloud.console.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity(name = "p6e_message_log")
public class MessageLogModel implements Serializable {
    public static final String ID = "id";
    public static final String PID = "pid";
    public static final String TID = "tid";
    public static final String MARK = "mark";
    public static final String SOURCE = "source";
    public static final String STATUS = "status";
    public static final String CONTENT = "content";
    public static final String DATE = "date";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer pid;
    private Integer tid;
    private String mark;
    private String source;
    private Integer status;
    private String content;
    private LocalDateTime date;

    public MessageLogModel(String mark, Integer pid, Integer tid, String source) {
        this.mark = mark;
        this.pid = pid;
        this.tid = tid;
        this.source = source;
    }
}
