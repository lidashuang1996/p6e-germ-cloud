package club.p6e.germ.cloud.console.domain.entity.message;

import club.p6e.germ.cloud.console.infrastructure.model.MessageTemplateModel;
import club.p6e.germ.cloud.console.infrastructure.repository.MessageTemplateRepository;
import com.p6e.germ.common.utils.P6eSpringUtil;

import java.util.Optional;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MessageTemplateEntity {

    private final MessageTemplateModel model;

    public MessageTemplateEntity(MessageTemplateModel model) {
        this.model = model;
    }

    public MessageTemplateEntity(Integer id) {
        final MessageTemplateRepository repository = P6eSpringUtil.getBean(MessageTemplateRepository.class);
        final Optional<MessageTemplateModel> optional = repository.findById(id);
        if (optional.isPresent()) {
            this.model = optional.get();
        } else {
            throw new RuntimeException();
        }
    }

    public MessageTemplateModel getModel() {
        return model;
    }
}
