package club.p6e.germ.message.console.service.impl;

import club.p6e.germ.message.LauncherPartModel;
import club.p6e.germ.message.model.MessageTemplateDb;
import club.p6e.germ.message.mybatis.mapper.MessageTemplateMapper;
import club.p6e.germ.message.cache.ICacheTemplate;
import club.p6e.germ.message.console.model.MessageConsoleTemplateModel;
import club.p6e.germ.message.console.service.MessageConsoleError;
import club.p6e.germ.message.console.service.MessageConsoleTemplateService;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eJsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class MessageConsoleTemplateServiceImpl implements MessageConsoleTemplateService {

    @Resource
    private MessageTemplateMapper mapper;

    @Resource
    private ICacheTemplate cacheTemplate;

    @Override
    public MessageConsoleTemplateModel.DtoListResult list(MessageConsoleTemplateModel.DtoParam param) {
        final MessageConsoleTemplateModel.DtoListResult result = new MessageConsoleTemplateModel.DtoListResult();
        final MessageTemplateDb db = P6eCopyUtil.run(param, MessageTemplateDb.class);
        final long count = mapper.count(db);
        final List<MessageTemplateDb> list = mapper.select(db);
        result.setTotal(count);
        result.setPage(db.getPage());
        result.setSize(db.getSize());
        result.setList(P6eCopyUtil.runList(list, MessageConsoleTemplateModel.Item.class, new ArrayList<>()));
        return result;
    }

    @Override
    public MessageConsoleTemplateModel.DtoResult create(MessageConsoleTemplateModel.DtoParam param) {
        final MessageConsoleTemplateModel.DtoResult result = new MessageConsoleTemplateModel.DtoResult();
        final MessageTemplateDb db = P6eCopyUtil.run(param, MessageTemplateDb.class);
        if (mapper.create(db) > 0) {
            final MessageTemplateDb rDb = mapper.selectById(db.getId());
            P6eCopyUtil.run(rDb, result);
        } else {
            result.setError(MessageConsoleError.DATABASE_EXCEPTION);
        }
        return result;
    }

    @Override
    public MessageConsoleTemplateModel.DtoResult delete(MessageConsoleTemplateModel.DtoParam param) {
        final MessageConsoleTemplateModel.DtoResult result = new MessageConsoleTemplateModel.DtoResult();
        final MessageTemplateDb db = P6eCopyUtil.run(param, MessageTemplateDb.class);
        final MessageTemplateDb rDb = mapper.selectById(db.getId());
        if (rDb == null) {
            result.setError(MessageConsoleError.RESOURCES_NO_EXIST_EXCEPTION);
        } else {
            if (mapper.delete(db) > 0) {
                P6eCopyUtil.run(rDb.setOperate(param.getOperate()), result);
            } else {
                result.setError(MessageConsoleError.DATABASE_EXCEPTION);
            }
        }
        return result;
    }

    @Override
    public MessageConsoleTemplateModel.DtoResult update(MessageConsoleTemplateModel.DtoParam param) {
        final MessageConsoleTemplateModel.DtoResult result = new MessageConsoleTemplateModel.DtoResult();
        final MessageTemplateDb db = P6eCopyUtil.run(param, MessageTemplateDb.class);
        final MessageTemplateDb rDb = mapper.selectById(db.getId());
        if (rDb == null) {
            result.setError(MessageConsoleError.RESOURCES_NO_EXIST_EXCEPTION);
        } else {
            final MessageTemplateDb uDb;
            if (mapper.update(db) > 0 && (uDb = mapper.selectById(db.getId())) != null) {
                P6eCopyUtil.run(uDb, result);
            } else {
                result.setError(MessageConsoleError.DATABASE_EXCEPTION);
            }
        }
        return result;
    }

    @Override
    public MessageConsoleTemplateModel.DtoResult resetCache(MessageConsoleTemplateModel.DtoParam param) {
        final MessageConsoleTemplateModel.DtoResult result = new MessageConsoleTemplateModel.DtoResult();
        final MessageTemplateDb db = P6eCopyUtil.run(param, MessageTemplateDb.class);
        final MessageTemplateDb rDb = mapper.selectById(db.getId());
        if (rDb == null) {
            result.setError(MessageConsoleError.RESOURCES_NO_EXIST_EXCEPTION);
        } else {
            cacheTemplate.set(String.valueOf(rDb.getId()),
                    P6eJsonUtil.toJson(P6eCopyUtil.run(rDb, LauncherPartModel.Template.class)));
        }
        return result;
    }
}
