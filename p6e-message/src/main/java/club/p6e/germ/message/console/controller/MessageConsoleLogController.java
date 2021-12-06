package club.p6e.germ.message.console.controller;

import club.p6e.germ.message.console.model.MessageConsoleLogModel;
import club.p6e.germ.message.console.service.MessageConsoleLogService;
import club.p6e.germ.message.http.HttpErrorModel;
import club.p6e.germ.message.http.HttpResultModel;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 日志接口
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/console/log")
public class MessageConsoleLogController {

    @Resource
    private MessageConsoleLogService service;

    @GetMapping("/")
    public HttpResultModel def(MessageConsoleLogModel.VoParam param) {
        return list(param);
    }

    @GetMapping("/list")
    public HttpResultModel list(MessageConsoleLogModel.VoParam param) {
        try {
            final MessageConsoleLogModel.DtoListResult result =
                    service.list(P6eCopyUtil.run(param, MessageConsoleLogModel.DtoParam.class));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleLogModel.VoListResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

}
