package club.p6e.germ.message.destination;

import java.util.List;
import java.util.Map;

/**
 * 终点处理
 * @author lidashuang
 * @version 1.0
 */
public interface DestinationInterface {

    /** 短信类型 */
    public static final String SMS_TYPE = "SMS";
    /** 邮件类型 */
    public static final String MAIL_TYPE = "MAIL";

    /**
     * 终点处理名称
     * @return 处理名称
     */
    public String name();

    /**
     * 终点处理类型
     * @return 处理类型
     */
    public String type();

    /**
     * 执行
     * @param id 操作编号
     * @param pid 平台编码
     * @param tid 模版编号
     * @param config 配置文件
     * @param recipients 收件人
     * @param title 标题
     * @param template 内容
     * @param templateParam 模版参数
     */
    public void execute(String id, Integer pid, Integer tid, Map<String, String> config, String source,
                        List<String> recipients, String title, String template, Map<String, String> templateParam);

}
