package club.p6e.germ.cloud.console.domain.aggregate.jurisdiction;

import club.p6e.germ.cloud.console.infrastructure.error.P6eResourceNoExistException;
import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUrlGroupRelationUrlKeyModel;
import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUrlGroupRelationUrlModel;
import club.p6e.germ.cloud.console.infrastructure.repository.JurisdictionUrlGroupRelationUrlRepository;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eSpringUtil;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author lidashuang
 * @version 1.0
 */
public class JurisdictionUrlGroupRelationUrlAggregate {

    /** 模型 */
    private final JurisdictionUrlGroupRelationUrlModel model;

    /**
     * 构造方法初始化
     * @param model 模型对象
     */
    public JurisdictionUrlGroupRelationUrlAggregate(JurisdictionUrlGroupRelationUrlModel model) {
        this.model = model;
    }

    /**
     * 构造方法初始化
     * @param gid 组 ID
     * @param uid URL ID
     */
    public JurisdictionUrlGroupRelationUrlAggregate(Integer gid, Integer uid) {
        this(new JurisdictionUrlGroupRelationUrlKeyModel(gid, uid));
    }

    /**
     * 构造方法初始化
     * @param id 模型 ID
     */
    public JurisdictionUrlGroupRelationUrlAggregate(JurisdictionUrlGroupRelationUrlKeyModel id) {
        final JurisdictionUrlGroupRelationUrlRepository repository = P6eSpringUtil.getBean(JurisdictionUrlGroupRelationUrlRepository.class);
        final Optional<JurisdictionUrlGroupRelationUrlModel> optional = repository.findById(id);
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
    public JurisdictionUrlGroupRelationUrlModel getModel() {
        return model;
    }

    /**
     * 创建
     * @return 实体对象
     */
    public JurisdictionUrlGroupRelationUrlAggregate create() {
        final JurisdictionUrlGroupRelationUrlRepository repository = P6eSpringUtil.getBean(JurisdictionUrlGroupRelationUrlRepository.class);
        // 验证参数
        // ....
        return new JurisdictionUrlGroupRelationUrlAggregate(repository.saveAndFlush(model));
    }

    /**
     * 删除
     * @param operate 操作人
     * @return 实体对象
     */
    public JurisdictionUrlGroupRelationUrlAggregate delete(String operate) {
        return null;
    }

    /**
     * 更新
     * @param m 模型对象
     * @return 实体对象
     */
    public JurisdictionUrlGroupRelationUrlAggregate update(JurisdictionUrlGroupRelationUrlModel m) {
        final JurisdictionUrlGroupRelationUrlKeyModel id = new JurisdictionUrlGroupRelationUrlKeyModel(m.getGid(), m.getUid());
        final JurisdictionUrlGroupRelationUrlRepository repository = P6eSpringUtil.getBean(JurisdictionUrlGroupRelationUrlRepository.class);
        final Optional<JurisdictionUrlGroupRelationUrlModel> optional = repository.findById(id);
        if (optional.isPresent()) {
            return new JurisdictionUrlGroupRelationUrlAggregate(repository.saveAndFlush(P6eCopyUtil.run(m, optional.get())));
        } else {
            throw new P6eResourceNoExistException(this.getClass().getName() + " update< " + id + " >.");
        }
    }
}
