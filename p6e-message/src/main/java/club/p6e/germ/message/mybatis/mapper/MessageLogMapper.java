package club.p6e.germ.message.mybatis.mapper;

import club.p6e.germ.message.model.MessageLogDb;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageLogMapper {

    /**
     * 创建
     * @param db 参数对象
     * @return 结果对象
     */
    public int create(MessageLogDb db);

    /**
     * 查询条数统计
     * @param db 参数对象
     * @return 结果对象
     */
    public long count(MessageLogDb db);

    /**
     * 查询
     * @param db 参数对象
     * @return 结果对象
     */
    public List<MessageLogDb> select(MessageLogDb db);

}
