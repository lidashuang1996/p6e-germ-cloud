package club.p6e.germ.cloud.core.content.service.impl;

import club.p6e.germ.cloud.core.content.service.LivePluginService;
import org.springframework.stereotype.Service;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class LivePluginDatabaseServiceImpl implements LivePluginService {

    @Override
    public int order() {
        return 1000;
    }

    @Override
    public void execute(String type, String rid, Object message) {
        System.out.println("DB " + type + "  " + rid + "  " + message);
    }

}
