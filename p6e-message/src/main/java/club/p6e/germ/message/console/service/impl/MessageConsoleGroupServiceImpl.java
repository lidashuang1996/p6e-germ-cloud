package club.p6e.germ.message.console.service.impl;

import club.p6e.germ.message.LauncherPartModel;
import club.p6e.germ.message.model.MessageGroupRelationPlatformDb;
import club.p6e.germ.message.mybatis.mapper.MessageGroupMapper;
import club.p6e.germ.message.mybatis.mapper.MessageGroupRelationPlatformMapper;
import club.p6e.germ.message.cache.ICacheGroup;
import club.p6e.germ.message.console.model.MessageConsoleGroupModel;
import club.p6e.germ.message.console.service.MessageConsoleError;
import club.p6e.germ.message.console.service.MessageConsoleGroupService;
import club.p6e.germ.message.model.MessageGroupDb;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eJsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class MessageConsoleGroupServiceImpl implements MessageConsoleGroupService {

    @Resource
    private ICacheGroup cacheGroup;

    @Resource
    private MessageGroupMapper groupMapper;
    
    @Resource
    private MessageGroupRelationPlatformMapper groupRelationPlatformMapper;

    @Override
    public MessageConsoleGroupModel.DtoListResult list(MessageConsoleGroupModel.DtoParam param) {
        final MessageConsoleGroupModel.DtoListResult result = new MessageConsoleGroupModel.DtoListResult();
        final MessageGroupDb db = P6eCopyUtil.run(param, MessageGroupDb.class);
        final long count = groupMapper.count(db);
        final List<MessageGroupDb> list = groupMapper.select(db);
        result.setTotal(count);
        result.setPage(db.getPage());
        result.setSize(db.getSize());
        result.setList(P6eCopyUtil.runList(list, MessageConsoleGroupModel.Item.class, new ArrayList<>()));
        return result;
    }

    @Override
    public MessageConsoleGroupModel.DtoListAllResult listAll(MessageConsoleGroupModel.DtoParam param) {
        final MessageConsoleGroupModel.DtoListAllResult result = new MessageConsoleGroupModel.DtoListAllResult();
        final MessageGroupDb db = P6eCopyUtil.run(param, MessageGroupDb.class);
        final long count = groupMapper.count(db);
        final List<MessageGroupDb> list = groupMapper.selectComplete(db);
        result.setTotal(count);
        result.setPage(db.getPage());
        result.setSize(db.getSize());
        result.setList(P6eCopyUtil.runList(list, MessageConsoleGroupModel.ItemAll.class, new ArrayList<>()));
        return result;
    }

    @Override
    public MessageConsoleGroupModel.DtoResult get(MessageConsoleGroupModel.DtoParam param) {
        final MessageConsoleGroupModel.DtoResult result = new MessageConsoleGroupModel.DtoResult();
        final MessageGroupDb db = P6eCopyUtil.run(param, MessageGroupDb.class);
        final MessageGroupDb rDb = groupMapper.selectById(db.getId());
        P6eCopyUtil.run(rDb, result);
        return result;
    }

    @Override
    public MessageConsoleGroupModel.DtoAllResult getAll(MessageConsoleGroupModel.DtoParam param) {
        final MessageConsoleGroupModel.DtoAllResult result = new MessageConsoleGroupModel.DtoAllResult();
        final MessageGroupDb db = P6eCopyUtil.run(param, MessageGroupDb.class);
        final MessageGroupDb rDb = groupMapper.selectByIdComplete(db.getId());
        P6eCopyUtil.run(rDb, result);
        return result;
    }

    @Override
    public MessageConsoleGroupModel.DtoResult create(MessageConsoleGroupModel.DtoParam param) {
        final MessageConsoleGroupModel.DtoResult result = new MessageConsoleGroupModel.DtoResult();
        final MessageGroupDb db = P6eCopyUtil.run(param, MessageGroupDb.class);
        if (groupMapper.create(db) > 0) {
            final MessageGroupDb rDb = groupMapper.selectById(db.getId());
            P6eCopyUtil.run(rDb, result);
        } else {
            result.setError(MessageConsoleError.DATABASE_EXCEPTION);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MessageConsoleGroupModel.DtoResult delete(MessageConsoleGroupModel.DtoParam param) {
        final MessageConsoleGroupModel.DtoResult result = new MessageConsoleGroupModel.DtoResult();
        final MessageGroupDb db = P6eCopyUtil.run(param, MessageGroupDb.class);
        final MessageGroupDb rDb = groupMapper.selectById(db.getId());
        if (rDb == null) {
            result.setError(MessageConsoleError.RESOURCES_NO_EXIST_EXCEPTION);
        } else {
            final MessageGroupRelationPlatformDb groupRelationPlatformDb = new MessageGroupRelationPlatformDb();
            groupRelationPlatformDb.setPage(1);
            groupRelationPlatformDb.setSize(1);
            groupRelationPlatformDb.setGid(rDb.getId());
            final List<MessageGroupRelationPlatformDb> groupRelationPlatformDbs = groupRelationPlatformMapper.select(groupRelationPlatformDb);
            if (groupRelationPlatformDbs != null && groupRelationPlatformDbs.size() > 0) {
                result.setError(MessageConsoleError.RESOURCES_EXIST_RELATION_EXCEPTION);
            } else {
                if (groupMapper.delete(db) <= 0
                        && groupRelationPlatformMapper.delete(groupRelationPlatformDb) <= 0) {
                    result.setError(MessageConsoleError.DATABASE_EXCEPTION);
                } else {
                    P6eCopyUtil.run(rDb.setOperate(param.getOperate()), result);
                }
            }
        }
        return result;
    }

    @Override
    public MessageConsoleGroupModel.DtoResult update(MessageConsoleGroupModel.DtoParam param) {
        final MessageConsoleGroupModel.DtoResult result = new MessageConsoleGroupModel.DtoResult();
        final MessageGroupDb db = P6eCopyUtil.run(param, MessageGroupDb.class);
        final MessageGroupDb rDb = groupMapper.selectById(db.getId());
        if (rDb == null) {
            result.setError(MessageConsoleError.RESOURCES_NO_EXIST_EXCEPTION);
        } else {
            final MessageGroupDb uDb;
            if (groupMapper.update(db) > 0 && (uDb = groupMapper.selectById(db.getId())) != null) {
                P6eCopyUtil.run(uDb, result);
            } else {
                result.setError(MessageConsoleError.DATABASE_EXCEPTION);
            }
        }
        return result;
    }

    @Override
    public MessageConsoleGroupModel.DtoResult resetCache(MessageConsoleGroupModel.DtoParam param) {
        final MessageConsoleGroupModel.DtoResult result = new MessageConsoleGroupModel.DtoResult();
        final MessageGroupDb db = P6eCopyUtil.run(param, MessageGroupDb.class);
        final MessageGroupDb rDb = groupMapper.selectByIdComplete(db.getId());
        if (rDb == null) {
            result.setError(MessageConsoleError.RESOURCES_NO_EXIST_EXCEPTION);
        } else {
            cacheGroup.set(String.valueOf(rDb.getId()),
                    P6eJsonUtil.toJson(P6eCopyUtil.run(db, LauncherPartModel.Group.class)));
        }
        return result;
    }
}
