package club.p6e.germ.message;

import club.p6e.germ.message.exception.LimitOverflowException;
import club.p6e.germ.message.limit.LimiterInterface;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 发送装置部分-组限流
 * @author lidashuang
 * @version 1.0
 */
@Component
public class PlatformGroupLimitLauncherPart implements LauncherPart {

    private static final String TYPE = "GROUP";

    /** 限流处理对象注入 */
    @Resource
    private LimiterInterface limiter;

    @Override
    public int order() {
        return 300;
    }

    @Override
    public void execute(LauncherPartModel model) throws LimitOverflowException {
        final String limit = model.getGroupLimit();
        final String groupId = String.valueOf(model.getGroupId());
        if (limiter.verification(limit, TYPE, groupId)) {
            model.setPlatformGroupLimitRoll(limiter.incr(TYPE, groupId));
        } else {
            throw new LimitOverflowException(this.getClass() + " group limit overflow error.");
        }
    }

    @Override
    public void error(LauncherPartModel model, Throwable e) {
        model.getPlatformGroupLimitRoll().decr();
    }
}
