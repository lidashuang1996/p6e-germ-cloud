package club.p6e.germ.message.limit;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface LimiterRollBackInterface {

    /**
     * 限流错误的递减
     */
    public void decr();

}
