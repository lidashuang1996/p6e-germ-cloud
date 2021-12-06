package club.p6e.germ.message;

import club.p6e.germ.message.exception.UnknownTypeException;
import com.p6e.germ.common.utils.P6eJsonUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 发送装置部分-平台
 * @author lidashuang
 * @version 1.0
 */
@Component
public class PlatformLauncherPart implements LauncherPart {

    @Override
    public int order() {
        return 500;
    }

    @Override
    public void execute(LauncherPartModel model) throws UnknownTypeException {
        final String type = model.getType();
        final int index = model.getSelectPlatformIndex();
        final List<LauncherPartModel.Platform> usePlatforms = model.getUsePlatforms();
        final LauncherPartModel.Platform platform = usePlatforms.get(index);
        final String actuator = platform.getActuator();
        model.setSelectPlatformId(platform.getId());
        model.setSelectPlatformLimit(platform.getLimit());
        switch (type) {
            case SMS_TYPE:
                final Map<String, String> sm =
                        P6eJsonUtil.fromJsonToMap(platform.getConfig(), String.class, String.class);
                model.setSmsConfig(sm);
                model.setSms(actuator);
                break;
            case MAIL_TYPE:
                final Map<String, String> mm =
                        P6eJsonUtil.fromJsonToMap(platform.getConfig(), String.class, String.class);
                model.setMailConfig(mm);
                model.setMail(actuator);
                break;
            default:
                throw new UnknownTypeException(this.getClass() + " type unknown error.");
        }
    }
}
