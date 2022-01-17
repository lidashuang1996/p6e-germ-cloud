package club.p6e.germ.cloud.console.application.service.impl;

import club.p6e.germ.cloud.console.application.service.MessageService;
import club.p6e.germ.cloud.console.controller.support.MessageContext;
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
    public MessageContext.Log.ListResultDto logList(MessageContext.Log.ParamDto param) {
        final MessageContext.Log.ListResultDto result = new MessageContext.Log.ListResultDto();
        final MessageLogAggregate messageLogAggregate = new MessageLogAggregate();
        final List<MessageLogModel> messageLogModelList = messageLogAggregate.getList();
        result.setPage(messageLogAggregate.getPage());
        result.setSize(messageLogAggregate.getSize());
        result.setTotal(messageLogAggregate.getTotal());
        final List<MessageContext.Log.Item> list = new ArrayList<>();
        for (final MessageLogModel model : messageLogModelList) {
            final List<MessageLogModel> collectionModel = new MessageLogEntity(model.getMark()).getCollection();
            final MessageContext.Log.Item item = P6eCopyUtil.run(model, MessageContext.Log.Item.class);
            final List<MessageContext.Log.Item2> contents = P6eCopyUtil.runList(collectionModel, MessageContext.Log.Item2.class);
            item.setDetails(contents);
            list.add(item);
        }
        result.setList(list);
        return result;
    }

    @Override
    public MessageContext.Platform.ResultDto platform(MessageContext.Platform.ParamDto param) {
        return P6eCopyUtil.run(new MessagePlatformEntity(param.getId()).getModel(), MessageContext.Platform.ResultDto.class);
    }

    @Override
    public MessageContext.Platform.ListResultDto platformList(MessageContext.Platform.ParamDto param) {
        final MessageContext.Platform.ListResultDto result = new MessageContext.Platform.ListResultDto();
        final MessagePlatformAggregate messagePlatformAggregate = new MessagePlatformAggregate();
        result.setPage(messagePlatformAggregate.getPage());
        result.setSize(messagePlatformAggregate.getSize());
        result.setTotal(messagePlatformAggregate.getTotal());
        result.setList(P6eCopyUtil.runList(messagePlatformAggregate.getList(), MessageContext.Platform.Item.class));
        return result;
    }

    @Override
    public MessageContext.Template.ResultDto template(MessageContext.Template.ParamDto param) {
        return P6eCopyUtil.run(new MessageTemplateEntity(param.getId()).getModel(), MessageContext.Template.ResultDto.class);
    }

    @Override
    public MessageContext.Template.ListResultDto templateList(MessageContext.Template.ParamDto param) {
        final MessageContext.Template.ListResultDto result = new MessageContext.Template.ListResultDto();
        final MessageTemplateAggregate messageTemplateAggregate = new MessageTemplateAggregate();
        result.setPage(messageTemplateAggregate.getPage());
        result.setSize(messageTemplateAggregate.getSize());
        result.setTotal(messageTemplateAggregate.getTotal());
        result.setList(P6eCopyUtil.runList(messageTemplateAggregate.getList(), MessageContext.Template.Item.class));
        return result;
    }

    @Override
    public MessageContext.Group.ListResultDto groupList(MessageContext.Group.ParamDto param) {
        final MessageContext.Group.ListResultDto result = new MessageContext.Group.ListResultDto();
        final MessageGroupAggregate messageGroupAggregate = new MessageGroupAggregate();
        result.setPage(messageGroupAggregate.getPage());
        result.setSize(messageGroupAggregate.getSize());
        result.setTotal(messageGroupAggregate.getTotal());
        result.setList(P6eCopyUtil.runList(messageGroupAggregate.getList(), MessageContext.Group.Item.class));
        return result;
    }

}
