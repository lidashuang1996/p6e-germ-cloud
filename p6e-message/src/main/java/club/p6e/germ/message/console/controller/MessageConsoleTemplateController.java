package club.p6e.germ.message.console.controller;

import club.p6e.germ.message.console.model.MessageConsoleTemplateModel;
import club.p6e.germ.message.console.service.MessageConsoleTemplateService;
import club.p6e.germ.message.http.HttpErrorModel;
import club.p6e.germ.message.http.HttpResultModel;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/console/template")
public class MessageConsoleTemplateController {

    /** 默认的操作人 */
    private static final String DEFAULT_OPERATE = "sys";

    @Resource
    private MessageConsoleTemplateService service;

    @GetMapping("/")
    public HttpResultModel def(MessageConsoleTemplateModel.VoParam param) {
        return list(param);
    }

    @GetMapping("/list")
    public HttpResultModel list(MessageConsoleTemplateModel.VoParam param) {
        try {
            final MessageConsoleTemplateModel.DtoListResult result =
                    service.list(P6eCopyUtil.run(param, MessageConsoleTemplateModel.DtoParam.class));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleTemplateModel.VoListResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @DeleteMapping("/delete/{id}")
    public HttpResultModel delete(String operate, @PathVariable Integer id) {
        try {
            if (operate == null) {
                operate = DEFAULT_OPERATE;
            }
            final MessageConsoleTemplateModel.DtoResult result =
                    service.delete(new MessageConsoleTemplateModel.DtoParam().setId(id).setOperate(operate));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleTemplateModel.VoResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @PostMapping("/create")
    public HttpResultModel create(String operate, @RequestBody MessageConsoleTemplateModel.VoParam param) {
        try {
            if (param == null
                    || param.getType() == null
                    || param.getName() == null
                    || param.getTitle() == null
                    || param.getParser() == null) {
                return HttpResultModel.build(HttpErrorModel.PARAMETER_EXCEPTION);
            } else {
                if (operate == null) {
                    operate = DEFAULT_OPERATE;
                }
                final MessageConsoleTemplateModel.DtoResult result =
                        service.create(P6eCopyUtil.run(param, MessageConsoleTemplateModel.DtoParam.class).setOperate(operate));
                if (result == null) {
                    return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
                } else if (result.getError() != null) {
                    return HttpResultModel.build(HttpErrorModel.create(result.getError()));
                } else {
                    return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleTemplateModel.VoResult.class));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @PutMapping("/update/{id}")
    public HttpResultModel update(String operate, @PathVariable Integer id, @RequestBody MessageConsoleTemplateModel.VoParam param) {
        try {
            if (operate == null) {
                operate = DEFAULT_OPERATE;
            }
            final MessageConsoleTemplateModel.DtoResult result =
                    service.update(P6eCopyUtil.run(param, MessageConsoleTemplateModel.DtoParam.class).setId(id).setOperate(operate));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleTemplateModel.VoResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @RequestMapping("/reset/cache/{id}")
    public HttpResultModel resetCache(@PathVariable Integer id) {
        try {
            final MessageConsoleTemplateModel.DtoResult result =
                    service.resetCache(new MessageConsoleTemplateModel.DtoParam().setId(id));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleTemplateModel.VoResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

}
