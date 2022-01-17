package club.p6e.germ.cloud.console.controller.message;

import club.p6e.germ.cloud.console.application.service.MessageService;
import club.p6e.germ.cloud.console.controller.support.ApiResultContext;
import club.p6e.germ.cloud.console.controller.support.BaseController;
import club.p6e.germ.cloud.console.controller.support.MessageContext;
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

    @RequestMapping(value = "/platform/{id}", method = { RequestMethod.GET, RequestMethod.POST })
    public ApiResultContext platform(@PathVariable Integer id) {
        return ApiResultContext.build(P6eCopyUtil.run(messageService.platform(
                new MessageContext.Platform.ParamDto().setId(id)), MessageContext.Platform.ResultVo.class));
    }

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

    @RequestMapping(value = "/template/{id}", method = { RequestMethod.GET, RequestMethod.POST })
    public ApiResultContext template(@PathVariable Integer id) {
        return ApiResultContext.build(P6eCopyUtil.run(messageService.template(
                new MessageContext.Template.ParamDto().setId(id)), MessageContext.Template.ResultVo.class));
    }

    @GetMapping("/template/list")
    public ApiResultContext getTemplateList(MessageContext.Template.ParamVo param) {
        return postTemplateList(param);
    }

    @PostMapping("/template/list")
    public ApiResultContext postTemplateList(@RequestBody MessageContext.Template.ParamVo param) {
        final MessageContext.Template.ListResultDto result =
                messageService.templateList(P6eCopyUtil.run(param, MessageContext.Template.ParamDto.class));
        return ApiResultContext.build(P6eCopyUtil.run(result, MessageContext.Template.ListResultVo.class));
    }


    @GetMapping("/group/list")
    public ApiResultContext getGroupList(MessageContext.Group.ParamVo param) {
        return postGroupList(param);
    }

    @PostMapping("/group/list")
    public ApiResultContext postGroupList(@RequestBody MessageContext.Group.ParamVo param) {
        final MessageContext.Group.ListResultDto result =
                messageService.groupList(P6eCopyUtil.run(param, MessageContext.Group.ParamDto.class));
        return ApiResultContext.build(P6eCopyUtil.run(result, MessageContext.Group.ListResultVo.class));
    }
}
