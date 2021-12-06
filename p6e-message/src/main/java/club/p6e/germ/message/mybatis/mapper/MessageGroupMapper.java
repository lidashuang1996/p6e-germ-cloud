package club.p6e.germ.message.mybatis.mapper;

import club.p6e.germ.message.model.MessageGroupDb;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageGroupMapper {

    /**
     * 根据 ID 查询完整数据
     * @param id ID 数据
     * @return 结果对象
     */
    public MessageGroupDb selectByIdComplete(Integer id);

    /**
     * 查询完整数据
     * @param db 参数对象
     * @return 结果对象
     */
    public List<MessageGroupDb> selectComplete(MessageGroupDb db);

    /**
     * 查询条数统计
     * @param db 参数对象
     * @return 结果对象
     */
    public long count(MessageGroupDb db);

    /**
     * 查询
     * @param db 参数对象
     * @return 结果对象
     */
    public List<MessageGroupDb> select(MessageGroupDb db);

    /**
     * 创建
     * @param db 参数对象
     * @return 结果对象
     */
    public int create(MessageGroupDb db);

    /**
     * 更新
     * @param db 参数对象
     * @return 结果对象
     */
    public int update(MessageGroupDb db);

    /**
     * ID 删除
     * @param db 参数对象
     * @return 结果对象
     */
    public int delete(MessageGroupDb db);

    /**
     * ID 查询
     * @param id 参数对象
     * @return 结果对象
     */
    public MessageGroupDb selectById(Integer id);
}
