package club.p6e.germ.message.console.controller;

import club.p6e.germ.message.console.model.MessageConsoleGroupModel;
import club.p6e.germ.message.console.service.MessageConsoleGroupService;
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
@RequestMapping("/console/group")
public class MessageConsoleGroupController {

    /** 默认的操作人 */
    private static final String DEFAULT_OPERATE = "sys";

    @Resource
    private MessageConsoleGroupService service;

    @GetMapping("/")
    public HttpResultModel def(MessageConsoleGroupModel.VoParam param) {
        return list(param);
    }

    @GetMapping("/list")
    public HttpResultModel list(MessageConsoleGroupModel.VoParam param) {
        try {
            final MessageConsoleGroupModel.DtoListResult result =
                    service.list(P6eCopyUtil.run(param, MessageConsoleGroupModel.DtoParam.class));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleGroupModel.VoListResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @GetMapping("/list/all")
    public HttpResultModel listAll(MessageConsoleGroupModel.VoParam param) {
        try {
            final MessageConsoleGroupModel.DtoListAllResult result =
                    service.listAll(P6eCopyUtil.run(param, MessageConsoleGroupModel.DtoParam.class));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleGroupModel.VoListAllResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @GetMapping("/get/{id}")
    public HttpResultModel get(@PathVariable Integer id) {
        try {
            final MessageConsoleGroupModel.DtoResult result =
                    service.get(new MessageConsoleGroupModel.DtoParam().setId(id));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleGroupModel.VoResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @GetMapping("/get/all/{id}")
    public HttpResultModel getAll(@PathVariable Integer id) {
        try {
            final MessageConsoleGroupModel.DtoAllResult result =
                    service.getAll(new MessageConsoleGroupModel.DtoParam().setId(id));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleGroupModel.VoAllResult.class));
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
            final MessageConsoleGroupModel.DtoResult result =
                    service.delete(new MessageConsoleGroupModel.DtoParam().setId(id).setOperate(operate));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleGroupModel.VoResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @PostMapping("/create")
    public HttpResultModel create(String operate, @RequestBody MessageConsoleGroupModel.VoParam param) {
        try {
            if (param == null
                    || param.getType() == null
                    || param.getName() == null
                    || param.getLimit() == null
                    || param.getRoute() == null
                    || param.getStatus() == null) {
                return HttpResultModel.build(HttpErrorModel.PARAMETER_EXCEPTION);
            } else {
                if (operate == null) {
                    operate = DEFAULT_OPERATE;
                }
                final MessageConsoleGroupModel.DtoResult result =
                        service.create(P6eCopyUtil.run(param, MessageConsoleGroupModel.DtoParam.class).setOperate(operate));
                if (result == null) {
                    return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
                } else if (result.getError() != null) {
                    return HttpResultModel.build(HttpErrorModel.create(result.getError()));
                } else {
                    return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleGroupModel.VoResult.class));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @PutMapping("/update/{id}")
    public HttpResultModel update(String operate, @PathVariable Integer id, @RequestBody MessageConsoleGroupModel.VoParam param) {
        try {
            if (operate == null) {
                operate = DEFAULT_OPERATE;
            }
            final MessageConsoleGroupModel.DtoResult result =
                    service.update(P6eCopyUtil.run(param, MessageConsoleGroupModel.DtoParam.class).setId(id).setOperate(operate));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleGroupModel.VoResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

    @RequestMapping("/reset/cache/{id}")
    public HttpResultModel resetCache(@PathVariable Integer id) {
        try {
            final MessageConsoleGroupModel.DtoResult result =
                    service.resetCache(new MessageConsoleGroupModel.DtoParam().setId(id));
            if (result == null) {
                return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
            } else if (result.getError() != null) {
                return HttpResultModel.build(HttpErrorModel.create(result.getError()));
            } else {
                return HttpResultModel.build(P6eCopyUtil.run(result, MessageConsoleGroupModel.VoResult.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResultModel.build(HttpErrorModel.SERVICE_EXCEPTION);
        }
    }

}
