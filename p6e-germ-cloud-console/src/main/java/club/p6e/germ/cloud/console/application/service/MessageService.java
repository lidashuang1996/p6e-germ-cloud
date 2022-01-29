package club.p6e.germ.cloud.console.application.service;

import club.p6e.germ.cloud.console.controller.support.MessageContext;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageService {

    public MessageContext.Log.ListResultDto logList(MessageContext.Log.ParamDto param);


    public MessageContext.Platform.ListResultDto platformList(MessageContext.Platform.ParamDto param);
    public MessageContext.Platform.ResultDto getPlatform(MessageContext.Platform.ParamDto param);
    public MessageContext.Platform.ResultDto createPlatform(MessageContext.Platform.ParamDto param);
    public MessageContext.Platform.ResultDto updatePlatform(MessageContext.Platform.ParamDto param);
    public MessageContext.Platform.ResultDto deletePlatform(MessageContext.Platform.ParamDto param);

    public MessageContext.PlatformGroup.ListResultDto platformGroupList(MessageContext.PlatformGroup.ParamDto param);
    public MessageContext.PlatformGroup.ResultDto getPlatformGroup(MessageContext.PlatformGroup.ParamDto param);
    public MessageContext.PlatformGroup.ResultDto createPlatformGroup(MessageContext.PlatformGroup.ParamDto param);
    public MessageContext.PlatformGroup.ResultDto updatePlatformGroup(MessageContext.PlatformGroup.ParamDto param);
    public MessageContext.PlatformGroup.ResultDto deletePlatformGroup(MessageContext.PlatformGroup.ParamDto param);


    public MessageContext.Template.ListResultDto templateList(MessageContext.Template.ParamDto param);
    public MessageContext.Template.ResultDto getTemplate(MessageContext.Template.ParamDto param);
    public MessageContext.Template.ResultDto createTemplate(MessageContext.Template.ParamDto param);
    public MessageContext.Template.ResultDto updateTemplate(MessageContext.Template.ParamDto param);
    public MessageContext.Template.ResultDto deleteTemplate(MessageContext.Template.ParamDto param);



}
