package club.p6e.germ.cloud.console.application.service;

import club.p6e.germ.cloud.console.controller.support.MessageContext;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageService {

    public MessageContext.Log.ListResultDto logList(MessageContext.Log.ParamDto param);

    public MessageContext.Platform.ResultDto platform(MessageContext.Platform.ParamDto param);
    public MessageContext.Platform.ListResultDto platformList(MessageContext.Platform.ParamDto param);

    public MessageContext.Template.ResultDto template(MessageContext.Template.ParamDto param);
    public MessageContext.Template.ListResultDto templateList(MessageContext.Template.ParamDto param);


    public MessageContext.Group.ListResultDto groupList(MessageContext.Group.ParamDto param);
}
