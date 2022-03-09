package club.p6e.germ.cloud.core.content.service;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface LivePluginService {

    /** 虎牙类型 */
    String HUYA_TYPE = "HUYA";
    /** 斗鱼类型 */
    String DOUYU_TYPE = "DOUYU";

    /**
     * 顺序
     * @return 顺序序号
     */
    public int order();

    /**
     * 执行的插件的内容
     * @param type 平台类型
     * @param rid 平台房间
     * @param message 消息内容
     */
    public void execute(String type, String rid, Object message);

}
