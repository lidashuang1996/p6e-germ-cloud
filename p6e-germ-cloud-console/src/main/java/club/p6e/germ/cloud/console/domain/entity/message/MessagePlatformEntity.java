package club.p6e.germ.cloud.console.domain.entity.message;

import club.p6e.germ.cloud.console.infrastructure.model.MessagePlatformModel;
import club.p6e.germ.cloud.console.infrastructure.repository.MessagePlatformRepository;
import com.p6e.germ.common.utils.P6eSpringUtil;

import java.util.Optional;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MessagePlatformEntity {

    private final MessagePlatformModel model;

    public MessagePlatformEntity(MessagePlatformModel model) {
        this.model = model;
    }

    public MessagePlatformEntity(Integer id) {
        final MessagePlatformRepository repository = P6eSpringUtil.getBean(MessagePlatformRepository.class);
        final Optional<MessagePlatformModel> optional = repository.findById(id);
        if (optional.isPresent()) {
            this.model = optional.get();
        } else {
            throw new RuntimeException();
        }
    }

    public MessagePlatformModel getModel() {
        return model;
    }
}
