package club.p6e.germ.message.mybatis.mapper;

import club.p6e.germ.message.model.MessagePlatformDb;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessagePlatformMapper {

    /**
     * 查询条数统计
     * @param db 参数对象
     * @return 结果对象
     */
    public long count(MessagePlatformDb db);

    /**
     * 查询
     * @param db 参数对象
     * @return 结果对象
     */
    public List<MessagePlatformDb> select(MessagePlatformDb db);

    /**
     * 更新
     * @param db 参数对象
     * @return 结果对象
     */
    public int update(MessagePlatformDb db);

    /**
     * 创建
     * @param db 参数对象
     * @return 结果对象
     */
    public int create(MessagePlatformDb db);

    /**
     * ID 删除
     * @param db 参数对象
     * @return 结果对象
     */
    public int delete(MessagePlatformDb db);

    /**
     * ID 查询
     * @param id 参数对象
     * @return 结果对象
     */
    public MessagePlatformDb selectById(Integer id);
}
