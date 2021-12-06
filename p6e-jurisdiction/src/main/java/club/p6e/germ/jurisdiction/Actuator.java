package club.p6e.germ.jurisdiction;

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface Actuator {

    public boolean execute(String method, String url, Integer uid, boolean defStatus, Map<String, String> param);

}
