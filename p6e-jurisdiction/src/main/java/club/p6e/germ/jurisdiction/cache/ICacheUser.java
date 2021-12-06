package club.p6e.germ.jurisdiction.cache;

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface ICacheUser {

    /** 时间 */
    public final long JURISDICTION_USER_TIME = 90000;
    /** 名称 */
    public final String JURISDICTION_USER_NAME = "JURISDICTION:USER:";

    public void set(String name, Map<String, String> value);

    public String get(String name, String key);

    public void del(String name);

    public boolean hasKey(String name);
}
