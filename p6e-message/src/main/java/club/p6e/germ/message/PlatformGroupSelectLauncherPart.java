package club.p6e.germ.message;

import club.p6e.germ.message.exception.*;
import club.p6e.germ.message.cache.ICacheGroup;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 发送装置部分-组选择平台
 * @author lidashuang
 * @version 1.0
 */
@Component
public class PlatformGroupSelectLauncherPart implements LauncherPart {

    /** 配置标识 */
    private static final String RANDOM = "random";
    private static final String TRAINING_ROTATION = "tr";
    private static final String WEIGHT_TRAINING_ROTATION = "wtr";
    /** 随机数 */
    private static final ThreadLocalRandom THREAD_LOCAL_RANDOM = ThreadLocalRandom.current();

    /** 缓存处理对象注入 */
    @Resource
    private ICacheGroup cache;

    @Override
    public int order() {
        return 400;
    }

    @Override
    public void execute(LauncherPartModel model) throws
            GroupFreezeException, GroupNullPointerException,
            UnknownException, UnknownTypeException, PartNullPointerException,
            PlatformNullPointerException, TemplateNullPointerException, LimitOverflowException {
        final String route = model.getGroupRoute();
        final String[] routes = route.split(",");
        if (model.getUsePlatforms() != null && model.getUsePlatforms().size() <= 0) {
            throw new PlatformNullPointerException(this.getClass() + " group platform list is null or size <= 0.");
        } else {
            switch (routes[0]) {
                case TRAINING_ROTATION:
                    executeTrainingRotation(model);
                    break;
                case WEIGHT_TRAINING_ROTATION:
                    executeWeightTrainingRotation(model);
                    break;
                case RANDOM:
                default:
                    executeRandom(model);
                    break;
            }
        }
    }

    /**
     * 轮训方法获取平台
     * @param model 零件对象
     */
    private void executeTrainingRotation(LauncherPartModel model) {
        final String type = model.getType();
        final String groupId = String.valueOf(model.getGroupId());
        final int useSize = model.getUsePlatforms().size();
        final long index = cache.operationIncr(type + ":" + groupId);
        model.setSelectPlatformIndex((int) index % useSize);
    }

    /**
     * 轮训加权方法获取平台
     * @param model 零件对象
     */
    private void executeWeightTrainingRotation(LauncherPartModel model) {
        final String type = model.getType();
        final String groupId = String.valueOf(model.getGroupId());
        final List<LauncherPartModel.Platform> usePlatforms = model.getUsePlatforms();
        int count = 0;
        for (final LauncherPartModel.Platform platform: usePlatforms) {
            count += platform.getWeight();
        }
        long index = cache.operationIncr(type + ":" + groupId);
        if (count == 0) {
            model.setSelectPlatformIndex((int) index % usePlatforms.size());
        } else {
            int sumWeight = 0;
            index = index % count;
            for (int i = 0; i < usePlatforms.size(); i++) {
                sumWeight += usePlatforms.get(i).getWeight();
                if (index < sumWeight) {
                    model.setSelectPlatformIndex(i);
                }
            }
        }
    }

    /**
     * 随机方法获取平台
     * @param model 零件对象
     */
    private void executeRandom(LauncherPartModel model) {
        final int useSize = model.getUsePlatforms().size();
        model.setSelectPlatformIndex(THREAD_LOCAL_RANDOM.nextInt(useSize));
    }
}
