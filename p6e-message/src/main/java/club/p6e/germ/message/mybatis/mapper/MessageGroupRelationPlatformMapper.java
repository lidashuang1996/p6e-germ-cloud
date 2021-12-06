package club.p6e.germ.message.mybatis.mapper;

import club.p6e.germ.message.model.MessageGroupRelationPlatformDb;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageGroupRelationPlatformMapper {

    public List<MessageGroupRelationPlatformDb> select(MessageGroupRelationPlatformDb db);

    public int create(MessageGroupRelationPlatformDb db);

    public int delete(MessageGroupRelationPlatformDb db);

}
