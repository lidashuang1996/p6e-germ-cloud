package club.p6e.germ.cloud.console.application.service.impl;

import club.p6e.germ.cloud.console.application.service.MessageService;
import club.p6e.germ.cloud.console.controller.support.MessageContext;
import club.p6e.germ.cloud.console.domain.aggregate.message.MessagePlatformGroupAggregate;
import club.p6e.germ.cloud.console.domain.aggregate.message.MessageLogAggregate;
import club.p6e.germ.cloud.console.domain.aggregate.message.MessagePlatformAggregate;
import club.p6e.germ.cloud.console.domain.aggregate.message.MessageTemplateAggregate;
import club.p6e.germ.cloud.console.domain.entity.message.MessageLogEntity;
import club.p6e.germ.cloud.console.domain.entity.message.MessagePlatformEntity;
import club.p6e.germ.cloud.console.domain.entity.message.MessagePlatformGroupEntity;
import club.p6e.germ.cloud.console.domain.entity.message.MessageTemplateEntity;
import club.p6e.germ.cloud.console.infrastructure.model.MessageLogModel;
import club.p6e.germ.cloud.console.infrastructure.model.MessagePlatformGroupModel;
import club.p6e.germ.cloud.console.infrastructure.model.MessagePlatformModel;
import club.p6e.germ.cloud.console.infrastructure.model.MessageTemplateModel;
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
        final MessageLogAggregate messageLogAggregate = new MessageLogAggregate(
                param.getSearch(),
                param.getPage(),
                param.getSize()
        );
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
    public MessageContext.Platform.ListResultDto platformList(MessageContext.Platform.ParamDto param) {
        final MessageContext.Platform.ListResultDto result = new MessageContext.Platform.ListResultDto();
        final MessagePlatformAggregate messagePlatformAggregate = new MessagePlatformAggregate(
                param.getSearch(),
                param.getType(),
                param.getStatus(),
                param.getPage(),
                param.getSize()
        );
        result.setPage(messagePlatformAggregate.getPage());
        result.setSize(messagePlatformAggregate.getSize());
        result.setTotal(messagePlatformAggregate.getTotal());
        result.setList(P6eCopyUtil.runList(messagePlatformAggregate.getList(), MessageContext.Platform.Item.class));
        return result;
    }

    @Override
    public MessageContext.Platform.ResultDto getPlatform(MessageContext.Platform.ParamDto param) {
        return P6eCopyUtil.run(
                new MessagePlatformEntity(param.getId()).getModel(),
                MessageContext.Platform.ResultDto.class
        );
    }

    @Override
    public MessageContext.Platform.ResultDto createPlatform(MessageContext.Platform.ParamDto param) {
        return P6eCopyUtil.run(
                new MessagePlatformEntity(
                        P6eCopyUtil.run(param, MessagePlatformModel.class)
                ).create().getModel(),
                MessageContext.Platform.ResultDto.class
        );
    }

    @Override
    public MessageContext.Platform.ResultDto updatePlatform(MessageContext.Platform.ParamDto param) {
        return P6eCopyUtil.run(
                new MessagePlatformEntity(param.getId()).update(
                        P6eCopyUtil.run(param, MessagePlatformModel.class)
                ).getModel(),
                MessageContext.Platform.ResultDto.class
        );
    }

    @Override
    public MessageContext.Platform.ResultDto deletePlatform(MessageContext.Platform.ParamDto param) {
        return P6eCopyUtil.run(
                new MessagePlatformEntity(param.getId()).delete(param.getOperate()).getModel(),
                MessageContext.Platform.ResultDto.class
        );
    }

    @Override
    public MessageContext.PlatformGroup.ListResultDto platformGroupList(MessageContext.PlatformGroup.ParamDto param) {
        final MessageContext.PlatformGroup.ListResultDto result = new MessageContext.PlatformGroup.ListResultDto();
        final MessagePlatformGroupAggregate messageGroupAggregate = new MessagePlatformGroupAggregate(
                param.getSearch(),
                param.getType(),
                param.getStatus(),
                param.getPage(),
                param.getSize()
        );
        result.setPage(messageGroupAggregate.getPage());
        result.setSize(messageGroupAggregate.getSize());
        result.setTotal(messageGroupAggregate.getTotal());
        result.setList(P6eCopyUtil.runList(messageGroupAggregate.getList(), MessageContext.PlatformGroup.Item.class));
        return result;
    }

    @Override
    public MessageContext.PlatformGroup.ResultDto getPlatformGroup(MessageContext.PlatformGroup.ParamDto param) {
        return P6eCopyUtil.run(
                new MessagePlatformGroupEntity(param.getId()).getModel(),
                MessageContext.PlatformGroup.ResultDto.class
        );
    }

    @Override
    public MessageContext.PlatformGroup.ResultDto createPlatformGroup(MessageContext.PlatformGroup.ParamDto param) {
        return P6eCopyUtil.run(
                new MessagePlatformGroupEntity(
                        P6eCopyUtil.run(param, MessagePlatformGroupModel.class)
                ).create().getModel(),
                MessageContext.PlatformGroup.ResultDto.class
        );
    }

    @Override
    public MessageContext.PlatformGroup.ResultDto updatePlatformGroup(MessageContext.PlatformGroup.ParamDto param) {
        return P6eCopyUtil.run(
                new MessagePlatformGroupEntity(param.getId()).update(
                        P6eCopyUtil.run(param, MessagePlatformGroupModel.class)
                ).getModel(),
                MessageContext.PlatformGroup.ResultDto.class
        );
    }

    @Override
    public MessageContext.PlatformGroup.ResultDto deletePlatformGroup(MessageContext.PlatformGroup.ParamDto param) {
        return P6eCopyUtil.run(
                new MessagePlatformGroupEntity(param.getId()).delete(param.getOperate()).getModel(),
                MessageContext.PlatformGroup.ResultDto.class
        );
    }

    @Override
    public MessageContext.Template.ListResultDto templateList(MessageContext.Template.ParamDto param) {
        final MessageContext.Template.ListResultDto result = new MessageContext.Template.ListResultDto();
        final MessageTemplateAggregate messageTemplateAggregate = new MessageTemplateAggregate(
                param.getSearch(),
                param.getType(),
                param.getPage(),
                param.getSize()
        );
        result.setPage(messageTemplateAggregate.getPage());
        result.setSize(messageTemplateAggregate.getSize());
        result.setTotal(messageTemplateAggregate.getTotal());
        result.setList(P6eCopyUtil.runList(messageTemplateAggregate.getList(), MessageContext.Template.Item.class));
        return result;
    }

    @Override
    public MessageContext.Template.ResultDto getTemplate(MessageContext.Template.ParamDto param) {
        return P6eCopyUtil.run(
                new MessageTemplateEntity(param.getId()).getModel(),
                MessageContext.Template.ResultDto.class
        );
    }

    @Override
    public MessageContext.Template.ResultDto createTemplate(MessageContext.Template.ParamDto param) {
        return P6eCopyUtil.run(
                new MessageTemplateEntity(
                        P6eCopyUtil.run(param, MessageTemplateModel.class)
                ).create().getModel(),
                MessageContext.Template.ResultDto.class
        );
    }

    @Override
    public MessageContext.Template.ResultDto updateTemplate(MessageContext.Template.ParamDto param) {
        return P6eCopyUtil.run(
                new MessageTemplateEntity(param.getId()).update(
                        P6eCopyUtil.run(param, MessageTemplateModel.class)
                ).getModel(),
                MessageContext.Template.ResultDto.class
        );
    }

    @Override
    public MessageContext.Template.ResultDto deleteTemplate(MessageContext.Template.ParamDto param) {
        return P6eCopyUtil.run(
                new MessageTemplateEntity(param.getId()).delete(param.getOperate()).getModel(),
                MessageContext.Template.ResultDto.class
        );
    }

}
