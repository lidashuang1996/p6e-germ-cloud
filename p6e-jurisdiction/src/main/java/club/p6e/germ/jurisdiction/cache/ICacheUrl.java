package club.p6e.germ.jurisdiction.cache;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface ICacheUrl {

    /** 时间 */
    public final long JURISDICTION_CACHE_URL_TIME = 90000;
    /** 名称 */
    public final String JURISDICTION_CACHE_URL_NAME = "JURISDICTION:CACHE:URL:";

    public void set(String key, String value);

    public String get(String key);

}
