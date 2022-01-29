package club.p6e.germ.cloud.console.controller.message;

import club.p6e.germ.cloud.console.application.service.MessageService;
import club.p6e.germ.cloud.console.controller.support.ApiResultContext;
import club.p6e.germ.cloud.console.controller.support.BaseController;
import club.p6e.germ.cloud.console.controller.support.MessageContext;
import club.p6e.germ.cloud.console.infrastructure.model.ErrorModel;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Resource
    private MessageService messageService;

    /*--------------[ log (START)]--------------*/

    @GetMapping("/log/list")
    public ApiResultContext getLogList(MessageContext.Log.ParamVo param) {
        return postLogList(param);
    }

    @PostMapping("/log/list")
    public ApiResultContext postLogList(@RequestBody MessageContext.Log.ParamVo param) {
        final MessageContext.Log.ListResultDto result =
                messageService.logList(P6eCopyUtil.run(param, MessageContext.Log.ParamDto.class));
        return ApiResultContext.build(P6eCopyUtil.run(result, MessageContext.Log.ListResultVo.class));
    }

    /*--------------[ log (END)]--------------*/





    /*--------------[ platform (START)]--------------*/

    @GetMapping("/platform/list")
    public ApiResultContext getPlatformList(MessageContext.Platform.ParamVo param) {
        return postPlatformList(param);
    }

    @PostMapping("/platform/list")
    public ApiResultContext postPlatformList(@RequestBody MessageContext.Platform.ParamVo param) {
        final MessageContext.Platform.ListResultDto result =
                messageService.platformList(P6eCopyUtil.run(param, MessageContext.Platform.ParamDto.class));
        return ApiResultContext.build(P6eCopyUtil.run(result, MessageContext.Platform.ListResultVo.class));
    }

    @PostMapping(value = "/platform/create")
    public ApiResultContext createPlatform(@RequestBody MessageContext.Platform.ParamVo param) {
        if (param == null
                || param.getName() == null
                || param.getLimit() == null
                || param.getType() == null
                || param.getWeight() == null
                || param.getStatus() == null
                || param.getConfig() == null
                || param.getActuator() == null) {
            return ApiResultContext.build(ErrorModel.PARAMETER_EXCEPTION);
        }
        return ApiResultContext.build(P6eCopyUtil.run(
                messageService.createPlatform(P6eCopyUtil.run(param,
                        MessageContext.Platform.ParamDto.class).setOperate(DEFAULT_OPERATE)), MessageContext.Platform.ResultVo.class));
    }

    @RequestMapping(value = "/platform/get/{id}", method = { RequestMethod.GET, RequestMethod.POST })
    public ApiResultContext getPlatform(@PathVariable Integer id) {
        return ApiResultContext.build(P6eCopyUtil.run(messageService.getPlatform(
                new MessageContext.Platform.ParamDto().setId(id)), MessageContext.Platform.ResultVo.class));
    }

    @RequestMapping(value = "/platform/update/{id}", method = { RequestMethod.POST, RequestMethod.PUT })
    public ApiResultContext updatePlatform(@PathVariable Integer id, @RequestBody MessageContext.Platform.ParamVo param) {
        if (param == null
                || param.getName() == null
                || param.getType() == null
                || param.getLimit() == null
                || param.getWeight() == null
                || param.getStatus() == null
                || param.getConfig() == null
                || param.getActuator() == null) {
            return ApiResultContext.build(ErrorModel.PARAMETER_EXCEPTION);
        }
        return ApiResultContext.build(P6eCopyUtil.run(
                messageService.updatePlatform(P6eCopyUtil.run(param,
                        MessageContext.Platform.ParamDto.class).setId(id).setOperate(DEFAULT_OPERATE)), MessageContext.Platform.ResultVo.class));
    }

    @RequestMapping(value = "/platform/delete/{id}", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
    public ApiResultContext deletePlatform(@PathVariable Integer id) {
        return ApiResultContext.build(P6eCopyUtil.run(messageService.deletePlatform(
                new MessageContext.Platform.ParamDto().setId(id).setOperate(DEFAULT_OPERATE)), MessageContext.Platform.ResultVo.class));
    }

    /*--------------[ platform (END)]--------------*/





    /*--------------[ platform group (START)]--------------*/

    @GetMapping("/platform/group/list")
    public ApiResultContext getPlatformGroupList(MessageContext.PlatformGroup.ParamVo param) {
        return postPlatformGroupList(param);
    }

    @PostMapping("/platform/group/list")
    public ApiResultContext postPlatformGroupList(@RequestBody MessageContext.PlatformGroup.ParamVo param) {
        final MessageContext.PlatformGroup.ListResultDto result =
                messageService.platformGroupList(P6eCopyUtil.run(param, MessageContext.PlatformGroup.ParamDto.class));
        return ApiResultContext.build(P6eCopyUtil.run(result, MessageContext.PlatformGroup.ListResultVo.class));
    }

    @PostMapping(value = "/platform/group/create")
    public ApiResultContext createPlatformGroup(@RequestBody MessageContext.PlatformGroup.ParamVo param) {
        if (param == null
                || param.getName() == null
                || param.getType() == null
                || param.getStatus() == null
                || param.getRoute() == null
                || param.getLimit() == null) {
            return ApiResultContext.build(ErrorModel.PARAMETER_EXCEPTION);
        }
        return ApiResultContext.build(P6eCopyUtil.run(
                messageService.createPlatformGroup(P6eCopyUtil.run(param,
                        MessageContext.PlatformGroup.ParamDto.class).setOperate(DEFAULT_OPERATE)), MessageContext.PlatformGroup.ResultVo.class));
    }

    @RequestMapping(value = "/platform/group/get/{id}", method = { RequestMethod.GET, RequestMethod.POST })
    public ApiResultContext getPlatformGroup(@PathVariable Integer id) {
        return ApiResultContext.build(P6eCopyUtil.run(messageService.getPlatformGroup(
                new MessageContext.PlatformGroup.ParamDto().setId(id)), MessageContext.PlatformGroup.ResultVo.class));
    }

    @RequestMapping(value = "/platform/group/update/{id}", method = { RequestMethod.POST, RequestMethod.PUT })
    public ApiResultContext updatePlatformGroup(@PathVariable Integer id, @RequestBody MessageContext.PlatformGroup.ParamVo param) {
        if (param == null
                || param.getType() == null
                || param.getName() == null
                || param.getStatus() == null
                || param.getRoute() == null
                || param.getLimit() == null) {
            return ApiResultContext.build(ErrorModel.PARAMETER_EXCEPTION);
        }
        return ApiResultContext.build(P6eCopyUtil.run(messageService.updatePlatformGroup(P6eCopyUtil.run(param,
                MessageContext.PlatformGroup.ParamDto.class).setId(id).setOperate(DEFAULT_OPERATE)), MessageContext.PlatformGroup.ResultVo.class));
    }

    @RequestMapping(value = "/platform/group/delete/{id}", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
    public ApiResultContext deletePlatformGroup(@PathVariable Integer id) {
        return ApiResultContext.build(P6eCopyUtil.run(messageService.deletePlatformGroup(
                new MessageContext.PlatformGroup.ParamDto().setId(id).setOperate(DEFAULT_OPERATE)), MessageContext.PlatformGroup.ResultVo.class));
    }

    @GetMapping("/platform/group/relation/create")
    public ApiResultContext createRelation(MessageContext.PlatformGroup.ParamVo param) {
        return postPlatformGroupList(param);
    }

    @GetMapping("/platform/group/relation/delete")
    public ApiResultContext deleteRelation(MessageContext.PlatformGroup.ParamVo param) {
        return postPlatformGroupList(param);
    }

    /*--------------[ platform group (END)]--------------*/





    /*--------------[ template (START)]--------------*/

    @GetMapping(value = "/template/list")
    public ApiResultContext getTemplateList(MessageContext.Template.ParamVo param) {
        return postTemplateList(param);
    }

    @PostMapping(value = "/template/list")
    public ApiResultContext postTemplateList(@RequestBody MessageContext.Template.ParamVo param) {
        final MessageContext.Template.ListResultDto result =
                messageService.templateList(P6eCopyUtil.run(param, MessageContext.Template.ParamDto.class));
        return ApiResultContext.build(P6eCopyUtil.run(result, MessageContext.Template.ListResultVo.class));
    }

    @PostMapping(value = "/template/create")
    public ApiResultContext createTemplate(@RequestBody MessageContext.Template.ParamVo param) {
        if (param == null
                || param.getName() == null
                || param.getType() == null
                || param.getTitle() == null
                || param.getParser() == null
                || param.getContent() == null) {
            return ApiResultContext.build(ErrorModel.PARAMETER_EXCEPTION);
        }
        return ApiResultContext.build(P6eCopyUtil.run(
                messageService.createTemplate(P6eCopyUtil.run(param,
                        MessageContext.Template.ParamDto.class).setOperate(DEFAULT_OPERATE)), MessageContext.Template.ResultVo.class));
    }

    @RequestMapping(value = "/template/get/{id}", method = { RequestMethod.GET, RequestMethod.POST })
    public ApiResultContext getTemplate(@PathVariable Integer id) {
        return ApiResultContext.build(P6eCopyUtil.run(messageService.getTemplate(
                new MessageContext.Template.ParamDto().setId(id)), MessageContext.Template.ResultVo.class));
    }

    @RequestMapping(value = "/template/update/{id}", method = { RequestMethod.POST, RequestMethod.PUT })
    public ApiResultContext updateTemplate(@PathVariable Integer id, @RequestBody MessageContext.Template.ParamVo param) {
        if (param == null
                || param.getName() == null
                || param.getType() == null
                || param.getTitle() == null
                || param.getParser() == null
                || param.getContent() == null) {
            return ApiResultContext.build(ErrorModel.PARAMETER_EXCEPTION);
        }
        return ApiResultContext.build(P6eCopyUtil.run(messageService.updateTemplate(
                P6eCopyUtil.run(param, MessageContext.Template.ParamDto.class).setId(id).setOperate(DEFAULT_OPERATE)), MessageContext.Template.ResultVo.class));
    }

    @RequestMapping(value = "/template/delete/{id}", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
    public ApiResultContext deleteTemplate(@PathVariable Integer id) {
        return ApiResultContext.build(P6eCopyUtil.run(messageService.deleteTemplate(
                new MessageContext.Template.ParamDto().setId(id).setOperate(DEFAULT_OPERATE)), MessageContext.Template.ResultVo.class));
    }

    /*--------------[ template (END)]--------------*/
}
