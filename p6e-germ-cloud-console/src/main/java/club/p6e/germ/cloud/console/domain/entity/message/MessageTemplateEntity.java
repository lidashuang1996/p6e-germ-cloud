package club.p6e.germ.cloud.console.domain.entity.message;

import club.p6e.germ.cloud.console.infrastructure.model.MessageTemplateModel;
import club.p6e.germ.cloud.console.infrastructure.repository.MessageTemplateRepository;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eSpringUtil;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MessageTemplateEntity {

    /** 模型 */
    private final MessageTemplateModel model;

    /**
     * 构造方法初始化
     * @param model 模型对象
     */
    public MessageTemplateEntity(MessageTemplateModel model) {
        this.model = model;
    }

    /**
     * 构造方法初始化
     * @param id 模型 ID
     */
    public MessageTemplateEntity(Integer id) {
        final MessageTemplateRepository repository = P6eSpringUtil.getBean(MessageTemplateRepository.class);
        final Optional<MessageTemplateModel> optional = repository.findById(id);
        if (optional.isPresent()) {
            this.model = optional.get();
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * 获取模型对象
     * @return 模型对象
     */
    public MessageTemplateModel getModel() {
        return model;
    }

    /**
     * 创建
     * @return 实体对象
     */
    public MessageTemplateEntity create() {
        final MessageTemplateRepository repository = P6eSpringUtil.getBean(MessageTemplateRepository.class);
        model.setId(null); // 删除 ID 让其自增
        model.setIsDelete(0);
        model.setCreateDate(LocalDateTime.now());
        model.setUpdateDate(LocalDateTime.now());
        return new MessageTemplateEntity(repository.saveAndFlush(model));
    }

    /**
     * 删除
     * @param operate 操作人
     * @return 实体对象
     */
    public MessageTemplateEntity delete(String operate) {
        final MessageTemplateRepository repository = P6eSpringUtil.getBean(MessageTemplateRepository.class);
        final Optional<MessageTemplateModel> optional = repository.findById(model.getId());
        if (optional.isPresent()) {
            repository.saveAndFlush(
                    optional.get()
                            .setIsDelete(1)
                            .setOperate(operate)
                            .setUpdateDate(LocalDateTime.now())
            );
            return this;
        }
        throw new RuntimeException();
    }

    /**
     * 更新
     * @param m 模型对象
     * @return 实体对象
     */
    public MessageTemplateEntity update(MessageTemplateModel m) {
        final MessageTemplateRepository repository = P6eSpringUtil.getBean(MessageTemplateRepository.class);
        final Optional<MessageTemplateModel> optional = repository.findById(model.getId());
        if (optional.isPresent()) {
            m.setId(null); // 删除 ID
            m.setUpdateDate(LocalDateTime.now());
            return new MessageTemplateEntity(repository.saveAndFlush(P6eCopyUtil.run(m, optional.get())));
        }
        throw new RuntimeException();
    }
}
