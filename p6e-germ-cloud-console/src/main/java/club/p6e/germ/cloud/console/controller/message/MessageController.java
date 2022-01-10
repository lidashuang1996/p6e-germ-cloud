package club.p6e.germ.cloud.console.controller.message;

import club.p6e.germ.cloud.console.application.service.MessageService;
import club.p6e.germ.cloud.console.controller.support.ApiResultModel;
import club.p6e.germ.cloud.console.controller.support.BaseController;
import club.p6e.germ.cloud.console.controller.support.MessageModel;
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
    public ApiResultModel getLogList(MessageModel.Log.ParamVo param) {
        return postLogList(param);
    }

    @PostMapping("/log/list")
    public ApiResultModel postLogList(@RequestBody MessageModel.Log.ParamVo param) {
        final MessageModel.Log.ListResultDto result =
                messageService.logList(P6eCopyUtil.run(param, MessageModel.Log.ParamDto.class));
        return ApiResultModel.build(P6eCopyUtil.run(result, MessageModel.Log.ListResultVo.class));
    }



    @RequestMapping(value = "/platform/{id}", method = { RequestMethod.GET, RequestMethod.POST })
    public ApiResultModel platform(@PathVariable Integer id) {
        return ApiResultModel.build(P6eCopyUtil.run(messageService.platform(
                new MessageModel.Platform.ParamDto().setId(id)), MessageModel.Platform.ResultVo.class));
    }

    @GetMapping("/platform/list")
    public ApiResultModel getPlatformList(MessageModel.Platform.ParamVo param) {
        return postPlatformList(param);
    }

    @PostMapping("/platform/list")
    public ApiResultModel postPlatformList(@RequestBody MessageModel.Platform.ParamVo param) {
        final MessageModel.Platform.ListResultDto result =
                messageService.platformList(P6eCopyUtil.run(param, MessageModel.Platform.ParamDto.class));
        return ApiResultModel.build(P6eCopyUtil.run(result, MessageModel.Platform.ListResultVo.class));
    }



    @RequestMapping(value = "/template/{id}", method = { RequestMethod.GET, RequestMethod.POST })
    public ApiResultModel template(@PathVariable Integer id) {
        return ApiResultModel.build(P6eCopyUtil.run(messageService.template(
                new MessageModel.Template.ParamDto().setId(id)), MessageModel.Template.ResultVo.class));
    }

    @GetMapping("/template/list")
    public ApiResultModel getTemplateList(MessageModel.Template.ParamVo param) {
        return postTemplateList(param);
    }

    @PostMapping("/template/list")
    public ApiResultModel postTemplateList(@RequestBody MessageModel.Template.ParamVo param) {
        final MessageModel.Template.ListResultDto result =
                messageService.templateList(P6eCopyUtil.run(param, MessageModel.Template.ParamDto.class));
        return ApiResultModel.build(P6eCopyUtil.run(result, MessageModel.Template.ListResultVo.class));
    }


    @GetMapping("/group/list")
    public ApiResultModel getGroupList(MessageModel.Group.ParamVo param) {
        return postGroupList(param);
    }

    @PostMapping("/group/list")
    public ApiResultModel postGroupList(@RequestBody MessageModel.Group.ParamVo param) {
        final MessageModel.Group.ListResultDto result =
                messageService.groupList(P6eCopyUtil.run(param, MessageModel.Group.ParamDto.class));
        return ApiResultModel.build(P6eCopyUtil.run(result, MessageModel.Group.ListResultVo.class));
    }
}
