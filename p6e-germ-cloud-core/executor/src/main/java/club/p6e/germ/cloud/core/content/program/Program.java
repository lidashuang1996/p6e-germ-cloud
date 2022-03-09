package club.p6e.germ.cloud.core.content.program;

import club.p6e.germ.cloud.core.content.service.LivePluginService;
import club.p6e.live.room.LiveRoomMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public abstract class Program {

    /** 虎牙类型 */
    protected static final String HUYA_TYPE = "HUYA";
    /** 斗鱼类型 */
    protected static final String DOUYU_TYPE = "DOUYU";

    /** 注册的插件列表 */
    protected static final List<LivePluginService> PLUGINS = new ArrayList<>();

    /**
     * 初始化
     * @param plugins 注册的插件
     */
    public static void init(LivePluginService... plugins) {
        init(Arrays.asList(plugins));
    }

    /**
     * 初始化
     * @param plugins 注册的插件
     */
    public synchronized static void init(Collection<LivePluginService> plugins) {
        for (LivePluginService plugin : plugins) {
            boolean bool = true;
            for (int i = 0; i < PLUGINS.size(); i++) {
                if (plugin.order() > PLUGINS.get(i).order()) {
                    PLUGINS.add(i, plugin);
                    bool = false;
                    break;
                }
            }
            if (bool) {
                PLUGINS.add(plugin);
            }
        }
    }

    /**
     * 收到消息后执行注册的插件
     * @param type 平台类型
     * @param rid 平台房间
     * @param messages 消息内容对象
     */
    public static void execute(String type, String rid, List<? extends LiveRoomMessage> messages) {
        for (final LiveRoomMessage message : messages) {
            for (final LivePluginService plugin : PLUGINS) {
                try {
                    plugin.execute(type, rid, message.data());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
