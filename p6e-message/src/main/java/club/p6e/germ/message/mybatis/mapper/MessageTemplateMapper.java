package club.p6e.germ.message.mybatis.mapper;

import club.p6e.germ.message.model.MessageTemplateDb;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageTemplateMapper {

    /**
     * 查询条数统计
     * @param db 参数对象
     * @return 结果对象
     */
    public long count(MessageTemplateDb db);

    /**
     * 查询
     * @param db 参数对象
     * @return 结果对象
     */
    public List<MessageTemplateDb> select(MessageTemplateDb db);

    /**
     * 创建
     * @param db 参数对象
     * @return 结果对象
     */
    public int create(MessageTemplateDb db);

    /**
     * 更新
     * @param db 参数对象
     * @return 结果对象
     */
    public int update(MessageTemplateDb db);

    /**
     * ID 删除
     * @param db 参数对象
     * @return 结果对象
     */
    public int delete(MessageTemplateDb db);

    /**
     * ID 查询
     * @param id 参数对象
     * @return 结果对象
     */
    public MessageTemplateDb selectById(Integer id);
}
