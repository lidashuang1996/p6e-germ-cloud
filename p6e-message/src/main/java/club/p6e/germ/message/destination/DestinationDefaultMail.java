package club.p6e.germ.message.destination;

import club.p6e.germ.message.model.MessageLogDb;
import club.p6e.germ.message.mybatis.mapper.MessageLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.*;

/**
 * 默认终点邮件
 * @author lidashuang
 * @version 1.0
 */
@Component("DestinationDefaultMail")
public class DestinationDefaultMail implements DestinationInterface {

    /** 默认名称 */
    private static final String DEFAULT_NAME = "__default__";

    /** 注入日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(DestinationDefaultMail.class);

    @Resource
    private MessageLogMapper mapper;

    @Override
    public String name() {
        return DEFAULT_NAME;
    }

    @Override
    public String type() {
        return MAIL_TYPE;
    }

    @Override
    public void execute(String id, Integer pid, Integer tid, Map<String, String> config, String source,
                        List<String> recipients, String title, String template, Map<String, String> templateParam) {
        final MessageLogDb db = new MessageLogDb();
        mapper.create(db.setPid(pid).setTid(tid).setMark(id).setSource(source)
                .setStatus(10).setContent("[MAIL] create task => title: " + title + ", param: " + templateParam));

        DestinationFactory.task(() -> {
            Transport transport = null;
            try {
                LOGGER.info("start mail [ " + id + " ] ==> id: " + id + ", pid: " + pid
                        + ", tid: " + tid + ", " + "recipients: " + recipients + ", "
                        + "title: " + title + ", template: " + template + ", templateParam: " + templateParam);
                mapper.create(db.setStatus(20).setContent("[MAIL] performing task => recipients: " + recipients));

                // 1. 连接上发送邮件的服务器
                // 创建属性文件对象
                final Properties properties = new Properties();
                for (final String key : config.keySet()) {
                    if (!key.startsWith("$")) {
                        properties.setProperty(key, config.get(key));
                    }
                }

                final String name = config.get("$name");
                final String user = config.get("$user");
                final String from = config.get("$from");
                final String password = config.get("$password");
                if (user == null || from == null || password == null) {
                    throw new NullPointerException(this.getClass() + " mail param is null.");
                }
                // 连接邮件的服务器，会话
                final Session session = Session.getDefaultInstance(properties);
                // 获取到传输对象
                transport = session.getTransport();
                // 在这里两个参数，第一个参数是你的邮箱，第二个参数是授权码
                transport.connect(user, password);

                // 2. 构建出一封邮件（设置收件人、设置主题和设置正文）
                final MimeMessage message = new MimeMessage(session);
                // 设置发件人
                // 这里的参数是发件人邮箱
                message.setFrom(new InternetAddress(MimeUtility.encodeText(name) + " <" + from + ">"));
                // 设置收件人
                final List<InternetAddress> r = new ArrayList<>();
                for (String recipient : recipients) {
                    r.add(new InternetAddress(recipient));
                }
                message.setRecipients(Message.RecipientType.TO, r.toArray(new InternetAddress[0]));
                // 设置主题
                message.setSubject(title);
                // 设置正文
                message.setContent(template,"text/html;charset=UTF-8");

                // 3. 发送邮件
                transport.sendMessage(message, message.getAllRecipients());

                mapper.create(db.setStatus(30).setContent("[MAIL] complete task"));
                LOGGER.info("end sms [ " + id + " ] ==> success.");
            } catch (Exception e) {
                e.printStackTrace();
                mapper.create(db.setStatus(40).setContent("[MAIL] complete task error ==> " + e.getMessage()));
                LOGGER.info("end mail [ " + id + " ] ==> error.");
            } finally {
                if (transport != null) {
                    try {
                        transport.close();
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

// pggafgmwppyadibh
//    public static void main(String[] args) {
//        Map<String, String> config = new HashMap<>();
//        config.put("$name", "Li大会苏昂AA");
//        config.put("$user", "2499087056@qq.com");
//        config.put("$from", "2499087056@qq.com");
//        config.put("$password", "pggafgmwppyadibh");
//        config.put("mail.host", "smtp.qq.com");
//        config.put("mail.transport.protocol", "smtp");
//        config.put("mail.smtp.auth", "true");
//        List<String> recipients = new ArrayList<>();
//        recipients.add("1294935733@qq.com");
//        String title = "邮件标题.😡";
//        String template = "邮件内容哈哈哈啊测试代码 enenneeenaaaa..😡";
//        Transport transport = null;
//        try {
//            // 1. 连接上发送邮件的服务器
//            // 创建属性文件对象
//            final Properties properties = new Properties();
//            // 邮件服务器主机
//            // properties.setProperty("mail.host","smtp.qq.com");
//            // 邮件传输协议
//            // properties.setProperty("mail.transport.protocol","smtp");
//            // 设置邮件发送需要认证
//            // properties.setProperty("mail.smtp.auth","true");
//            for (final String key : config.keySet()) {
//                if (!key.startsWith("$")) {
//                    properties.setProperty(key, config.get(key));
//                }
//            }
//
//            final String name = config.get("$name");
//            final String user = config.get("$user");
//            final String from = config.get("$from");
//            final String password = config.get("$password");
//            if (name == null || user == null || from == null || password == null) {
//                return;
//            }
//
//            // 连接邮件的服务器，会话
//            final Session session = Session.getDefaultInstance(properties);
//            // 获取到传输对象
//            transport = session.getTransport();
//            // 在这里两个参数，第一个参数是你的邮箱，第二个参数是授权码
//            transport.connect(user, password);
//            // 2. 构建出一封邮件（设置收件人、设置主题和设置正文）
//            // 有邮件的类
//            final MimeMessage message = new MimeMessage(session);
//            // 设置发件人
//            // 这里的参数是发件人邮箱
//            message.setFrom(new InternetAddress(MimeUtility.encodeText(name) + " <" + from + ">"));
//            // 设置收件人
//            final List<InternetAddress> r = new ArrayList<>();
//            for (String recipient : recipients) {
//                r.add(new InternetAddress(recipient));
//            }
//            message.setRecipients(Message.RecipientType.TO, r.toArray(new InternetAddress[0]));
//            // 设置主题
//            message.setSubject(title);
//            // 设置正文
//            message.setContent(template,"text/html;charset=UTF-8");
//            // 3. 发送邮件
//            transport.sendMessage(message, message.getAllRecipients());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (transport != null) {
//                try {
//                    transport.close();
//                } catch (MessagingException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
