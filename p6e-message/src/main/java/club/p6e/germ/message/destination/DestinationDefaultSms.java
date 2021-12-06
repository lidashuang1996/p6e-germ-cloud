package club.p6e.germ.message.destination;

import club.p6e.germ.message.model.MessageLogDb;
import club.p6e.germ.message.mybatis.mapper.MessageLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 默认终点短信
 * @author lidashuang
 * @version 1.0
 */
@Component("DestinationDefaultSms")
public class DestinationDefaultSms implements DestinationInterface {
    /** 默认名称 */
    private static final String DEFAULT_NAME = "__default__";
    /** 注入日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(DestinationDefaultSms.class);

    @Resource
    private MessageLogMapper mapper;

    @Override
    public String name() {
        return DEFAULT_NAME;
    }

    @Override
    public String type() {
        return SMS_TYPE;
    }

    @Override
    public void execute(String id, Integer pid, Integer tid, Map<String, String> config, String source,
                        List<String> recipients, String title, String template, Map<String, String> templateParam) {
        final MessageLogDb db = new MessageLogDb();
        mapper.create(db.setPid(pid).setTid(tid).setMark(id).setSource(source)
                .setStatus(10).setContent("[SMS] create task => pid: " + pid + ", tid: " + tid));
        DestinationFactory.task(() -> {
            try {
                LOGGER.info("start mail [ " + id + " ] ==> id: " + id + ", pid: " + pid
                        + ", tid: " + tid + ", " + "recipients: " + recipients + ", "
                        + "title: " + title + ", template: " + template + ", templateParam: " + templateParam);
                mapper.create(db.setStatus(20).setContent("[SMS] performing task => recipients: " + recipients));

                Thread.sleep(1500);

                mapper.create(db.setStatus(30).setContent("[SMS] complete task"));
                LOGGER.info("end sms [ " + id + " ] ==> success.");
            } catch (Exception e) {
                e.printStackTrace();
                mapper.create(db.setStatus(40).setContent("[SMS] complete task error ==> " + e.getMessage()));
                LOGGER.info("end sms [ " + id + " ] ==> error.");
            }
        });
    }
}
