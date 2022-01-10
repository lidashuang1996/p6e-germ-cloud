package club.p6e.germ.cloud.console.application.service;

import club.p6e.germ.cloud.console.controller.support.MessageModel;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageService {

    public MessageModel.Log.ListResultDto logList(MessageModel.Log.ParamDto param);

    public MessageModel.Platform.ResultDto platform(MessageModel.Platform.ParamDto param);
    public MessageModel.Platform.ListResultDto platformList(MessageModel.Platform.ParamDto param);

    public MessageModel.Template.ResultDto template(MessageModel.Template.ParamDto param);
    public MessageModel.Template.ListResultDto templateList(MessageModel.Template.ParamDto param);


    public MessageModel.Group.ListResultDto groupList(MessageModel.Group.ParamDto param);
}
