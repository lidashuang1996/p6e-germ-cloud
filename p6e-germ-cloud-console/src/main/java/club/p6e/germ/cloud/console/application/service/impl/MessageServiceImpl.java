package club.p6e.germ.cloud.console.application.service.impl;

import club.p6e.germ.cloud.console.application.service.MessageService;
import club.p6e.germ.cloud.console.controller.support.MessageModel;
import club.p6e.germ.cloud.console.domain.aggregate.message.MessageGroupAggregate;
import club.p6e.germ.cloud.console.domain.aggregate.message.MessageLogAggregate;
import club.p6e.germ.cloud.console.domain.aggregate.message.MessagePlatformAggregate;
import club.p6e.germ.cloud.console.domain.aggregate.message.MessageTemplateAggregate;
import club.p6e.germ.cloud.console.domain.entity.message.MessageLogEntity;
import club.p6e.germ.cloud.console.domain.entity.message.MessagePlatformEntity;
import club.p6e.germ.cloud.console.domain.entity.message.MessageTemplateEntity;
import club.p6e.germ.cloud.console.infrastructure.model.MessageLogModel;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Override
    public MessageModel.Log.ListResultDto logList(MessageModel.Log.ParamDto param) {
        final MessageModel.Log.ListResultDto result = new MessageModel.Log.ListResultDto();
        final MessageLogAggregate messageLogAggregate = new MessageLogAggregate();
        final List<MessageLogModel> messageLogModelList = messageLogAggregate.getList();
        result.setPage(messageLogAggregate.getPage());
        result.setSize(messageLogAggregate.getSize());
        result.setTotal(messageLogAggregate.getTotal());
        final List<MessageModel.Log.Item> list = new ArrayList<>();
        for (final MessageLogModel model : messageLogModelList) {
            final List<MessageLogModel> collectionModel = new MessageLogEntity(model.getMark()).getCollection();
            final MessageModel.Log.Item item = P6eCopyUtil.run(model, MessageModel.Log.Item.class);
            final List<MessageModel.Log.Item2> contents = P6eCopyUtil.runList(collectionModel, MessageModel.Log.Item2.class);
            item.setDetails(contents);
            list.add(item);
        }
        result.setList(list);
        return result;
    }

    @Override
    public MessageModel.Platform.ResultDto platform(MessageModel.Platform.ParamDto param) {
        return P6eCopyUtil.run(new MessagePlatformEntity(param.getId()).getModel(), MessageModel.Platform.ResultDto.class);
    }

    @Override
    public MessageModel.Platform.ListResultDto platformList(MessageModel.Platform.ParamDto param) {
        final MessageModel.Platform.ListResultDto result = new MessageModel.Platform.ListResultDto();
        final MessagePlatformAggregate messagePlatformAggregate = new MessagePlatformAggregate();
        result.setPage(messagePlatformAggregate.getPage());
        result.setSize(messagePlatformAggregate.getSize());
        result.setTotal(messagePlatformAggregate.getTotal());
        result.setList(P6eCopyUtil.runList(messagePlatformAggregate.getList(), MessageModel.Platform.Item.class));
        return result;
    }

    @Override
    public MessageModel.Template.ResultDto template(MessageModel.Template.ParamDto param) {
        return P6eCopyUtil.run(new MessageTemplateEntity(param.getId()).getModel(), MessageModel.Template.ResultDto.class);
    }

    @Override
    public MessageModel.Template.ListResultDto templateList(MessageModel.Template.ParamDto param) {
        final MessageModel.Template.ListResultDto result = new MessageModel.Template.ListResultDto();
        final MessageTemplateAggregate messageTemplateAggregate = new MessageTemplateAggregate();
        result.setPage(messageTemplateAggregate.getPage());
        result.setSize(messageTemplateAggregate.getSize());
        result.setTotal(messageTemplateAggregate.getTotal());
        result.setList(P6eCopyUtil.runList(messageTemplateAggregate.getList(), MessageModel.Template.Item.class));
        return result;
    }

    @Override
    public MessageModel.Group.ListResultDto groupList(MessageModel.Group.ParamDto param) {
        final MessageModel.Group.ListResultDto result = new MessageModel.Group.ListResultDto();
        final MessageGroupAggregate messageGroupAggregate = new MessageGroupAggregate();
        result.setPage(messageGroupAggregate.getPage());
        result.setSize(messageGroupAggregate.getSize());
        result.setTotal(messageGroupAggregate.getTotal());
        result.setList(P6eCopyUtil.runList(messageGroupAggregate.getList(), MessageModel.Group.Item.class));
        return result;
    }

}
