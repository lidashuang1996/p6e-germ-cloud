package club.p6e.germ.message.limit;

/**
 * 限流接口
 * @author lidashuang
 * @version 1.0
 */
public interface LimiterInterface {

    /**
     * 递增
     * @param type 限流类型
     * @param id 限流 ID
     * @return 限流器
     */
    public LimiterRollBackInterface incr(String type, String id);

    /**
     * 限流验证
     * @param limit 限流规则
     * @param type 限流类型
     * @param id 限流 ID
     * @return 是否被限流
     */
    public boolean verification(String limit, String type, String id);
}
