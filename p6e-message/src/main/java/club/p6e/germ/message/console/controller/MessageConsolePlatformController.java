package club.p6e.germ.message.console.controller;

import club.p6e.germ.message.console.model.MessageConsolePlatformModel;
import club.p6e.germ.message.console.service.MessageConsolePlatformService;
import club.p6e.germ.message.http.HttpErrorModel;
import club.p6e.germ.message.http.HttpResultModel;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/console/platform")
public class MessageConsolePlatformController {

    /** 默认的操作人 */
    private static final String DEFAULT_OPERATE = "sys";

    @Resource
    private MessageConsolePlatformService service;

    @GetMapping("/")
    public HttpResultModel def(MessageConsolePlatformModel.VoParam param) {
        return list(param);
    }

    @GetMapping("/list")
    public HttpResultModel list(MessageConsolePlatformModel.VoParam param) {
        try {
            final MessageConsolePlatformModel.DtoListResult result =
                    service.list(P6eCopyUtil.run(param, MessageConsolePlatformModel.DtoParam.class));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsolePlatformModel.VoListResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @DeleteMapping("/delete/{id}")
    @Transactional(rollbackFor = Exception.class)
    public HttpResultModel delete(String operate, @PathVariable Integer id) {
        try {
            if (operate == null) {
                operate = DEFAULT_OPERATE;
            }
            final MessageConsolePlatformModel.DtoResult result =
                    service.delete(new MessageConsolePlatformModel.DtoParam().setId(id).setOperate(operate));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsolePlatformModel.VoResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @PostMapping("/create")
    public HttpResultModel create(String operate, @RequestBody MessageConsolePlatformModel.VoParam param) {
        try {
            if (param == null
                    || param.getActuator() == null
                    || param.getGroupId() == null
                    || param.getName() == null
                    || param.getLimit() == null
                    || param.getWeight() == null
                    || param.getConfig() == null
                    || param.getStatus() == null) {
                return HttpResultModel.build(HttpErrorModel.PARAMETER_EXCEPTION);
            } else {
                if (operate == null) {
                    operate = DEFAULT_OPERATE;
                }
                final MessageConsolePlatformModel.DtoResult result =
                        service.create(P6eCopyUtil.run(param, MessageConsolePlatformModel.DtoParam.class).setOperate(operate));
                if (result == null) {
                    return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
                } else if (result.getError() != null) {
                    return HttpResultModel.build(HttpErrorModel.create(result.getError()));
                } else {
                    return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsolePlatformModel.VoResult.class));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @PutMapping("/update/{id}")
    public HttpResultModel update(String operate, @PathVariable Integer id, @RequestBody MessageConsolePlatformModel.VoParam param) {
        try {
            if (operate == null) {
                operate = DEFAULT_OPERATE;
            }
            final MessageConsolePlatformModel.DtoResult result =
                    service.update(P6eCopyUtil.run(param, MessageConsolePlatformModel.DtoParam.class).setId(id).setOperate(operate));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsolePlatformModel.VoResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

}
