package club.p6e.germ.message;

import club.p6e.germ.message.exception.LimitOverflowException;
import club.p6e.germ.message.limit.LimiterInterface;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 发送装置部分-平台限流
 * @author lidashuang
 * @version 1.0
 */
@Component
public class PlatformLimitLauncherPart implements LauncherPart {

    private static final String TYPE = "PLATFORM";

    /** 限流处理对象注入 */
    @Resource
    private LimiterInterface limiter;

    @Override
    public int order() {
        return 600;
    }

    @Override
    public void execute(LauncherPartModel model) throws LimitOverflowException {
        final String limit = model.getSelectPlatformLimit();
        final String platformId = String.valueOf(model.getSelectPlatformId());
        if (limiter.verification(limit, TYPE, platformId)) {
            model.setSelectPlatformLimitRollBack(limiter.incr(TYPE, platformId));
        } else {
            throw new LimitOverflowException(this.getClass() + " platform limit overflow.");
        }
    }

    @Override
    public void error(LauncherPartModel model, Throwable e) {
        model.getSelectPlatformLimitRollBack().decr();
    }
}
