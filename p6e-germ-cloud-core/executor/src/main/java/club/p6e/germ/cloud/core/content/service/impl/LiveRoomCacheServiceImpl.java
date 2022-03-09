package club.p6e.germ.cloud.core.content.service.impl;

import club.p6e.germ.cloud.core.content.service.LiveRoomCacheService;
import club.p6e.germ.cloud.core.content.service.LiveRoomService;
import com.p6e.germ.common.utils.P6eJsonUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class LiveRoomCacheServiceImpl implements LiveRoomCacheService {

    /**
     * 数据模型
     */
    @Data
    @Accessors(chain = true)
    public static class Model {
        private String type;
        private String rid;
    }

    /** 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(LiveRoomCacheServiceImpl.class);

    /** 内存缓存对象 */
    private static final List<Model> CACHE = new ArrayList<>();

    /** 文件缓存对象 */
    private static File FILE = null;
    /** 文件缓存状态对象锁 */
    private static final String FILE_STATUS = "FILE_STATUS";

    /** 服务对象 */
    @Resource
    private LiveRoomService service;

    /**
     * 添加模型
     * @param model 数据模型
     */
    public static void add(Model model) {
        synchronized (CACHE) {
            boolean bool = true;
            for (Model m : CACHE) {
                if (m.getRid().equals(model.getRid())) {
                    bool = false;
                    break;
                }
            }
            if (bool) {
                CACHE.add(model);
            }
        }
    }

    /**
     * 删除模型
     * @param model 数据模型
     */
    public static void remove(Model model) {
        synchronized (CACHE) {
            for (int i = 0; i < CACHE.size(); i++) {
                if (CACHE.get(i).getRid().equals(model.getRid())) {
                    CACHE.remove(CACHE.get(i));
                    break;
                }
            }
        }
    }

    /**
     * 文件读取
     * @param file 文件对象
     */
    private static void fileRead(File file) {
        synchronized (FILE_STATUS) {
            FileInputStream fis = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            try{
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                br = new BufferedReader(isr);
                String line;
                while((line = br.readLine()) != null){
                    try {
                        add(P6eJsonUtil.fromJson(line, Model.class));
                    } catch (Exception e) {
                        // 忽略异常
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (isr != null) {
                    try {
                        isr.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (br != null) {
                    try {
                        br.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 文件缓存写入
     * @param content 写入的内容
     */
    private static void fileWriter(String content) {
        synchronized (FILE_STATUS) {
            FileWriter fw = null;
            try {
                if (FILE != null) {
                    fw = new FileWriter(FILE, true);
                    fw.append(content).append("\r\n");
                    fw.flush();
                }
            } catch (Exception e) {
                // 忽略异常
            } finally {
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 内存数据写入到文件缓存
     */
    private static void fileCacheWriter() {
        synchronized (FILE_STATUS) {
            FileWriter fw = null;
            try {
                if (FILE != null) {
                    fw = new FileWriter(FILE);
                    for (final Model model : CACHE) {
                        fw.append(P6eJsonUtil.toJson(model)).append("\r\n");
                    }
                    fw.flush();
                }
            } catch (Exception e) {
                // 忽略异常
            } finally {
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 初始化
     */
    public void init(String index) {
        try {
            final File file = new File("./live_room_config_" + index + ".txt");
            if (!file.exists()) {
                LOGGER.info("live_room_config_" + index + ".txt no exists.");
                LOGGER.info("live_room_config_" + index + ".txt create file ==> " + file.createNewFile());
            }
            // 初始化文件对象
            FILE = file;
            // 读取缓存数据
            fileRead(file);
            // 重新连接之前的房间数据
            for (final Model model : CACHE) {
                service.create(model.getType(), model.getRid());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> list() {
        final Map<String, String> result = new HashMap<>();
        final List<String> list = service.list();
        for (final Model model : CACHE) {
            boolean bool = false;
            for (final String rid : list) {
                if (rid.equals(model.getRid())) {
                    bool = true;
                    result.put(model.getRid(), "1");
                    break;
                }
            }
            if (!bool) {
                result.put(model.getRid(), "0");
            }
        }
        return result;
    }

    @Override
    public Map<String, String> list(String type) {
        final Map<String, String> result = new HashMap<>();
        final List<String> list = service.list(type);
        for (final Model model : CACHE) {
            if (type == null || type.equals(model.getType())) {
                boolean bool = true;
                for (final String rid : list) {
                    if (rid.equals(model.getRid())) {
                        bool = false;
                        result.put(model.getRid(), "1");
                        break;
                    }
                }
                if (bool) {
                    result.put(model.getRid(), "0");
                }
            }
        }
        return result;
    }

    @Override
    public void create(String type, String rid) {
        final Model model = new Model().setRid(rid).setType(type);
        fileWriter(P6eJsonUtil.toJson(model));
        add(model);
        service.create(type, rid);
    }

    @Override
    public void delete(String type, String rid) {
        remove(new Model().setRid(rid).setType(type));
        fileCacheWriter();
        service.delete(type, rid);
    }

}
