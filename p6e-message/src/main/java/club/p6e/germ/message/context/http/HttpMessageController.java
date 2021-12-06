package club.p6e.germ.message.context.http;

import club.p6e.germ.message.config.Config;
import club.p6e.germ.message.Launcher;
import club.p6e.germ.message.exception.*;
import club.p6e.germ.message.http.HttpErrorModel;
import club.p6e.germ.message.http.HttpResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 发送邮件/短信
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/")
public class HttpMessageController {

    /** 短信类型 */
    private static final String SMS_TYPE = "SMS";
    /** 邮件类型 */
    private static final String MAIL_TYPE = "MAIL";

    /** 注入发射器对象 */
    private final Launcher launcher;
    /** 注入配置文件对象 */
    private final Config.Context config;

    @Autowired
    public HttpMessageController(Launcher launcher, Config config) {
        this.launcher = launcher;
        this.config = config.getContext();
    }

    @RequestMapping(value = "/sms/{group}", method = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST } )
    public HttpResultModel sms(@PathVariable String group, @RequestBody HttpMessageModel model) {
        return execute(SMS_TYPE, Integer.valueOf(group), model);
    }

    @RequestMapping(value = "/mail/{group}", method = { RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST } )
    public HttpResultModel mail(@PathVariable String group, @RequestBody HttpMessageModel model) {
        return execute(MAIL_TYPE, Integer.valueOf(group), model);
    }

    /**
     * 执行发送
     * @param type 类型
     * @param group 组序号
     * @param model 模型对象
     * @return 结果模型对象
     */
    private HttpResultModel execute(String type, Integer group, HttpMessageModel model) {
        try {
            final Integer templateId = model.getTemplateId();
            final List<String> recipients = model.getRecipients();
            final Map<String, String> templateParams = model.getTemplateParams();
            if (recipients == null || recipients.size() <= 0
                    || recipients.size() > config.getMaxLengthRecipient() || templateId == null) {
                if (templateId == null) {
                    return HttpResultModel.build(HttpErrorModel.MESSAGE_TEMPLATE_ID_NULL_PARAMETER_EXCEPTION);
                }
                if (recipients == null || recipients.size() <= 0) {
                    return HttpResultModel.build(HttpErrorModel.MESSAGE_RECIPIENTS_NULL_PARAMETER_EXCEPTION);
                }
                if (recipients.size() > config.getMaxLengthRecipient()) {
                    return HttpResultModel.build(HttpErrorModel.MESSAGE_RECIPIENTS_LENGTH_OVERFLOW_PARAMETER_EXCEPTION);
                }
                return HttpResultModel.build(HttpErrorModel.PARAMETER_EXCEPTION);
            } else {
                try {
                    return HttpResultModel.build(launcher.execute("HTTP", type, group, recipients, templateId, templateParams));
                } catch (Exception e) {
                    e.printStackTrace();
                    if (e instanceof GroupFreezeException) {
                        return HttpResultModel.build(HttpErrorModel.MESSAGE_GROUP_FREEZE_EXCEPTION);
                    } else if (e instanceof GroupNullPointerException) {
                        return HttpResultModel.build(HttpErrorModel.MESSAGE_GROUP_NULL_POINTER_EXCEPTION);
                    } else if (e instanceof UnknownTypeException) {
                        return HttpResultModel.build(HttpErrorModel.MESSAGE_UNKNOWN_TYPE_EXCEPTION);
                    } else if (e instanceof PartNullPointerException) {
                        return HttpResultModel.build(HttpErrorModel.MESSAGE_PART_NULL_POINTER_EXCEPTION);
                    } else if (e instanceof PlatformNullPointerException) {
                        return HttpResultModel.build(HttpErrorModel.MESSAGE_PLATFORM_NULL_POINTER_EXCEPTION);
                    } else if (e instanceof TemplateNullPointerException) {
                        return HttpResultModel.build(HttpErrorModel.MESSAGE_TEMPLATE_NULL_POINTER_EXCEPTION);
                    } else if (e instanceof LimitOverflowException) {
                        return HttpResultModel.build(HttpErrorModel.MESSAGE_LIMIT_OVERFLOW_EXCEPTION);
                    } else {
                        return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

}
