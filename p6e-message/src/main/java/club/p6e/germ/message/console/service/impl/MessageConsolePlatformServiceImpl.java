package club.p6e.germ.message.console.service.impl;

import club.p6e.germ.message.console.model.MessageConsolePlatformModel;
import club.p6e.germ.message.model.MessageGroupRelationPlatformDb;
import club.p6e.germ.message.mybatis.mapper.MessageGroupMapper;
import club.p6e.germ.message.mybatis.mapper.MessageGroupRelationPlatformMapper;
import club.p6e.germ.message.console.service.MessageConsoleError;
import club.p6e.germ.message.console.service.MessageConsolePlatformService;
import club.p6e.germ.message.model.MessageGroupDb;
import club.p6e.germ.message.model.MessagePlatformDb;
import club.p6e.germ.message.mybatis.mapper.MessagePlatformMapper;
import com.p6e.germ.common.utils.P6eCopyUtil;
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
public class MessageConsolePlatformServiceImpl implements MessageConsolePlatformService {

    @Resource
    private MessageGroupMapper groupMapper;

    @Resource
    private MessagePlatformMapper platformMapper;

    @Resource
    private MessageGroupRelationPlatformMapper groupRelationPlatformMapper;

    @Override
    public MessageConsolePlatformModel.DtoListResult list(MessageConsolePlatformModel.DtoParam param) {
        final MessageConsolePlatformModel.DtoListResult result = new MessageConsolePlatformModel.DtoListResult();
        final MessagePlatformDb db = P6eCopyUtil.run(param, MessagePlatformDb.class);
        final long count = platformMapper.count(db);
        final List<MessagePlatformDb> list = platformMapper.select(db);
        result.setTotal(count);
        result.setPage(db.getPage());
        result.setSize(db.getSize());
        result.setList(P6eCopyUtil.runList(list, MessageConsolePlatformModel.Item.class, new ArrayList<>()));
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MessageConsolePlatformModel.DtoResult create(MessageConsolePlatformModel.DtoParam param) {
        final MessageConsolePlatformModel.DtoResult result = new MessageConsolePlatformModel.DtoResult();
        final MessagePlatformDb db = P6eCopyUtil.run(param, MessagePlatformDb.class);
        final MessageGroupDb groupDb = groupMapper.selectById(param.getGroupId());
        if (groupDb == null) {
            result.setError(MessageConsoleError.RESOURCES_NO_EXIST_EXCEPTION);
        } else {
            if (platformMapper.create(db) > 0) {
                final MessageGroupRelationPlatformDb messageGroupRelationPlatformDb =
                        new MessageGroupRelationPlatformDb().setGid(db.getGroupId()).setPid(db.getId());
                if (groupRelationPlatformMapper.create(messageGroupRelationPlatformDb) > 0) {
                    final MessagePlatformDb rDb = platformMapper.selectById(db.getId());
                    P6eCopyUtil.run(rDb, result);
                } else {
                    result.setError(MessageConsoleError.DATABASE_EXCEPTION);
                }
            } else {
                result.setError(MessageConsoleError.DATABASE_EXCEPTION);
            }
        }
        return result;
    }

    @Override
    public MessageConsolePlatformModel.DtoResult delete(MessageConsolePlatformModel.DtoParam param) {
        final MessageConsolePlatformModel.DtoResult result = new MessageConsolePlatformModel.DtoResult();
        final MessagePlatformDb db = P6eCopyUtil.run(param, MessagePlatformDb.class);
        final MessagePlatformDb rDb = platformMapper.selectById(db.getId());
        if (rDb == null) {
            result.setError(MessageConsoleError.RESOURCES_NO_EXIST_EXCEPTION);
        } else {
            if (platformMapper.delete(db) > 0) {
                final MessageGroupRelationPlatformDb groupRelationPlatformDb =
                        new MessageGroupRelationPlatformDb().setGid(rDb.getGroupId()).setPid(rDb.getId());
                if (groupRelationPlatformMapper.delete(groupRelationPlatformDb) > 0) {
                    P6eCopyUtil.run(rDb.setOperate(param.getOperate()), result);
                } else {
                    result.setError(MessageConsoleError.DATABASE_EXCEPTION);
                }
            } else {
                result.setError(MessageConsoleError.DATABASE_EXCEPTION);
            }
        }
        return result;
    }

    @Override
    public MessageConsolePlatformModel.DtoResult update(MessageConsolePlatformModel.DtoParam param) {
        final MessageConsolePlatformModel.DtoResult result = new MessageConsolePlatformModel.DtoResult();
        final MessagePlatformDb db = P6eCopyUtil.run(param, MessagePlatformDb.class);
        final MessagePlatformDb rDb = platformMapper.selectById(db.getId());
        if (rDb == null) {
            result.setError(MessageConsoleError.RESOURCES_NO_EXIST_EXCEPTION);
        } else {
            final MessagePlatformDb uDb;
            if (platformMapper.update(db) > 0 && (uDb = platformMapper.selectById(db.getId())) != null) {
                P6eCopyUtil.run(uDb, result);
            } else {
                result.setError(MessageConsoleError.DATABASE_EXCEPTION);
            }
        }
        return result;
    }
}
