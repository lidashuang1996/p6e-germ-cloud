package club.p6e.germ.message;

import club.p6e.germ.message.destination.DestinationFactory;
import club.p6e.germ.message.exception.UnknownTypeException;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 发送装置部分-终点
 * @author lidashuang
 * @version 1.0
 */
@Component("DestinationLauncherPart")
public class DestinationLauncherPart implements LauncherPart {

    @Override
    public int order() {
        return 700;
    }

    @Override
    public void execute(LauncherPartModel model) throws UnknownTypeException {
        final String type = model.getType();
        model.setOperation(UUID.randomUUID().toString().replace("-", ""));
        switch (type) {
            case SMS_TYPE:
                DestinationFactory.sms(model.getSms()).execute(
                        model.getOperation(),
                        model.getSelectPlatformId(),
                        model.getTemplateId(),
                        model.getSmsConfig(),
                        model.getSource(),
                        model.getRecipients(),
                        model.getTemplateTitle(),
                        model.getTemplateContent(),
                        model.getTemplateParam()
                );
                break;
            case MAIL_TYPE:
                DestinationFactory.mail(model.getMail()).execute(
                        model.getOperation(),
                        model.getSelectPlatformId(),
                        model.getTemplateId(),
                        model.getMailConfig(),
                        model.getSource(),
                        model.getRecipients(),
                        model.getTemplateTitle(),
                        model.getTemplateContent(),
                        model.getTemplateParam()
                );
                break;
            default:
                throw new UnknownTypeException(this.getClass() + " type unknown ...");
        }
    }
}
