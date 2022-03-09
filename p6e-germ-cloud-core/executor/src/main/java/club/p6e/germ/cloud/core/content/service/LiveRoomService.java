package club.p6e.germ.cloud.core.content.service;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface LiveRoomService {

    /** 虎牙类型 */
    String HUYA_TYPE = "HUYA";
    /** 斗鱼类型 */
    String DOUYU_TYPE = "DOUYU";

    /**
     * 列表
     * @return 结果对象
     */
    public List<String> list();

    /**
     * 列表
     * @param type 平台类型
     * @return 结果对象
     */
    public List<String> list(String type);

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

    /**
     * 重启
     * @param type 平台类型
     * @param rid 房间编号
     */
    public void restart(String type, String rid);

}
