package club.p6e.germ.cloud.core.content.service;

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface LiveRoomCacheService {

    /**
     * 列表
     * @return 结果对象
     */
    public Map<String, String> list();

    /**
     * 列表
     * @param type 平台类型
     * @return 结果对象
     */
    public Map<String, String> list(String type);

    /**
     * 创建
     * @param type 平台类型
     * @param rid 房间编号
     */
    public void create(String type, String rid);

    /**
     * 删除
     * @param type 平台类型
     * @param rid 房间编号
     */
    public void delete(String type, String rid);

}
