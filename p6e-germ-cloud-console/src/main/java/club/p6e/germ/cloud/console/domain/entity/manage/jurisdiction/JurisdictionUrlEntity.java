package club.p6e.germ.cloud.console.domain.entity.manage.jurisdiction;

import club.p6e.germ.cloud.console.infrastructure.error.P6eResourceNoExistException;
import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUrlModel;
import club.p6e.germ.cloud.console.infrastructure.repository.JurisdictionUrlRepository;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eSpringUtil;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author lidashuang
 * @version 1.0
 */
public class JurisdictionUrlEntity {

    /** 模型 */
    private final JurisdictionUrlModel model;

    /**
     * 构造方法初始化
     * @param model 模型对象
     */
    public JurisdictionUrlEntity(JurisdictionUrlModel model) {
        this.model = model;
    }

    /**
     * 构造方法初始化
     * @param id 模型 ID
     */
    public JurisdictionUrlEntity(Integer id) {
        final JurisdictionUrlRepository repository = P6eSpringUtil.getBean(JurisdictionUrlRepository.class);
        final Optional<JurisdictionUrlModel> optional = repository.findById(id);
        if (optional.isPresent()) {
            this.model = optional.get();
        } else {
            throw new P6eResourceNoExistException(this.getClass().getName() + " Construction< " + id + " >.");
        }
    }

    /**
     * 获取模型对象
     * @return 模型对象
     */
    public JurisdictionUrlModel getModel() {
        return model;
    }

    /**
     * 创建
     * @return 实体对象
     */
    public JurisdictionUrlEntity create() {
        final JurisdictionUrlRepository repository = P6eSpringUtil.getBean(JurisdictionUrlRepository.class);
        model.setId(null); // 删除 ID 让其自增
        model.setIsDelete(0); // 设置标记为未删除
        model.setCreateDate(LocalDateTime.now()); // 设置创建时间
        model.setUpdateDate(LocalDateTime.now()); // 设置更新时间
        // 验证参数
        // ....
        return new JurisdictionUrlEntity(repository.saveAndFlush(model));
    }

    /**
     * 删除
     * @param operate 操作人
     * @return 实体对象
     */
    public JurisdictionUrlEntity delete(String operate) {
        final JurisdictionUrlRepository repository = P6eSpringUtil.getBean(JurisdictionUrlRepository.class);
        final Optional<JurisdictionUrlModel> optional = repository.findById(model.getId());
        if (optional.isPresent()) {
            repository.saveAndFlush(
                    optional.get()
                            .setIsDelete(1)
                            .setOperate(operate)
                            .setUpdateDate(LocalDateTime.now())
            );
            return this;
        } else {
            throw new P6eResourceNoExistException(this.getClass().getName() + " delete< " + model.getId() + " >.");
        }
    }

    /**
     * 更新
     * @param m 模型对象
     * @return 实体对象
     */
    public JurisdictionUrlEntity update(JurisdictionUrlModel m) {
        final JurisdictionUrlRepository repository = P6eSpringUtil.getBean(JurisdictionUrlRepository.class);
        final Optional<JurisdictionUrlModel> optional = repository.findById(model.getId());
        if (optional.isPresent()) {
            m.setId(null); // 删除 ID 让其自增
            m.setUpdateDate(LocalDateTime.now()); // 设置更新时间
            return new JurisdictionUrlEntity(repository.saveAndFlush(P6eCopyUtil.run(m, optional.get())));
        } else {
            throw new P6eResourceNoExistException(this.getClass().getName() + " update< " + model.getId() + " >.");
        }
    }
}
