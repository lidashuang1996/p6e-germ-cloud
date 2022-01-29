package club.p6e.germ.cloud.console.domain.entity.manage.jurisdiction;

import club.p6e.germ.cloud.console.infrastructure.error.P6eResourceNoExistException;
import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUrlGroupModel;
import club.p6e.germ.cloud.console.infrastructure.repository.JurisdictionUrlGroupRepository;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eSpringUtil;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author lidashuang
 * @version 1.0
 */
public class JurisdictionUrlGroupEntity {

    /** 模型 */
    private final JurisdictionUrlGroupModel model;

    /**
     * 构造方法初始化
     * @param model 模型对象
     */
    public JurisdictionUrlGroupEntity(JurisdictionUrlGroupModel model) {
        this.model = model;
    }

    /**
     * 构造方法初始化
     * @param id 模型 ID
     */
    public JurisdictionUrlGroupEntity(Integer id) {
        final JurisdictionUrlGroupRepository repository = P6eSpringUtil.getBean(JurisdictionUrlGroupRepository.class);
        final Optional<JurisdictionUrlGroupModel> optional = repository.findById(id);
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
    public JurisdictionUrlGroupModel getModel() {
        return model;
    }

    /**
     * 创建
     * @return 实体对象
     */
    public JurisdictionUrlGroupEntity create() {
        final JurisdictionUrlGroupRepository repository = P6eSpringUtil.getBean(JurisdictionUrlGroupRepository.class);
        model.setId(null); // 删除 ID 让其自增
        model.setIsDelete(0); // 设置标记为未删除
        model.setCreateDate(LocalDateTime.now()); // 设置创建时间
        model.setUpdateDate(LocalDateTime.now()); // 设置更新时间
        // 验证参数
        // ....
        return new JurisdictionUrlGroupEntity(repository.saveAndFlush(model));
    }

    /**
     * 删除
     * @param operate 操作人
     * @return 实体对象
     */
    public JurisdictionUrlGroupEntity delete(String operate) {
        final JurisdictionUrlGroupRepository repository = P6eSpringUtil.getBean(JurisdictionUrlGroupRepository.class);
        final Optional<JurisdictionUrlGroupModel> optional = repository.findById(model.getId());
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
    public JurisdictionUrlGroupEntity update(JurisdictionUrlGroupModel m) {
        final JurisdictionUrlGroupRepository repository = P6eSpringUtil.getBean(JurisdictionUrlGroupRepository.class);
        final Optional<JurisdictionUrlGroupModel> optional = repository.findById(model.getId());
        if (optional.isPresent()) {
            m.setId(null); // 删除 ID 让其自增
            m.setUpdateDate(LocalDateTime.now()); // 设置更新时间
            return new JurisdictionUrlGroupEntity(repository.saveAndFlush(P6eCopyUtil.run(m, optional.get())));
        } else {
            throw new P6eResourceNoExistException(this.getClass().getName() + " update< " + model.getId() + " >.");
        }
    }
}
