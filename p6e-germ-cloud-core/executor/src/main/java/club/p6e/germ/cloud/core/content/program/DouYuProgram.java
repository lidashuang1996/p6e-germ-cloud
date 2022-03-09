package club.p6e.germ.cloud.core.content.program;

import club.p6e.live.room.LiveRoomCallback;
import club.p6e.live.room.platform.douyu.Application;
import club.p6e.live.room.platform.douyu.Client;
import club.p6e.live.room.platform.douyu.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public final class DouYuProgram extends Program {

    /** 缓存连接房间的客户端信息 */
    private static final Map<String, Application> CACHE = new HashMap<>();

    /** 读取连接房间的客户端信息 */
    public static Map<String, Application> get() {
        return CACHE;
    }

    /** 写入连接房间的客户端信息 */
    public static void put(String key, Application value) {
        synchronized (CACHE) {
            CACHE.put(key, value);
        }
    }

    /** 删除连接房间的客户端信息 */
    public static Application remove(String key) {
        synchronized (CACHE) {
            return CACHE.remove(key);
        }
    }

    /**
     * 创建连接房间的客户端
     * @param rid 房间编号
     * @return 房间客户端
     */
    public synchronized static Application create(String rid) {
        // 判断是否存在对应的房间
        if (CACHE.get(rid) == null) {
           final Application application = new Application(rid, new LiveRoomCallback.DouYu() {
               @Override
               public void onOpen(Client client) {

               }

               @Override
               public void onClose(Client client) {

               }

               @Override
               public void onError(Client client, Throwable throwable) {
                   throwable.printStackTrace();
               }

               @Override
               public void onMessage(Client client, List<Message> messages) {
                   execute(DOUYU_TYPE, rid, messages);
               }
           });
           // 写入缓存
           put(rid, application);
           // 连接
           application.connect();
           return application;
        } else {
           return CACHE.get(rid);
        }
    }

    /**
     * 删除连接房间的客户端信息
     * @param rid 房间编号
     */
    public static void delete(String rid) {
        final Application application = remove(rid);
        if (application != null) {
            application.shutdown();
        }
    }

    /**
     * 重置连接房间的客户端信息
     * @param rid 房间编号
     * @return 客户端信息
     */
    public static Application restart(String rid) {
        delete(rid);
        return create(rid);
    }
}
