package club.p6e.germ.cloud.console.domain.entity;

import club.p6e.germ.cloud.console.infrastructure.error.P6eResourceNoExistException;
import club.p6e.germ.cloud.console.infrastructure.model.DictionaryModel;
import club.p6e.germ.cloud.console.infrastructure.repository.DictionaryRepository;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eSpringUtil;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author lidashuang
 * @version 1.0
 */
public class DictionaryEntity {

    /** 模型 */
    private final DictionaryModel model;

    /**
     * 构造方法初始化
     * @param model 模型对象
     */
    public DictionaryEntity(DictionaryModel model) {
        this.model = model;
    }

    /**
     * 构造方法初始化
     * @param id 模型 ID
     */
    public DictionaryEntity(Integer id) {
        final DictionaryRepository repository = P6eSpringUtil.getBean(DictionaryRepository.class);
        final Optional<DictionaryModel> optional = repository.findById(id);
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
    public DictionaryModel getModel() {
        return model;
    }

    /**
     * 创建
     * @return 实体对象
     */
    public DictionaryEntity create() {
        final DictionaryRepository repository = P6eSpringUtil.getBean(DictionaryRepository.class);
        model.setId(null); // 删除 ID 让其自增
        model.setIsDelete(0); // 设置标记为未删除
        model.setCreateDate(LocalDateTime.now()); // 设置创建时间
        model.setUpdateDate(LocalDateTime.now()); // 设置更新时间
        // 验证参数
        // ....
        return new DictionaryEntity(repository.saveAndFlush(model));
    }

    /**
     * 删除
     * @param operate 操作人
     * @return 实体对象
     */
    public DictionaryEntity delete(String operate) {
        final DictionaryRepository repository = P6eSpringUtil.getBean(DictionaryRepository.class);
        final Optional<DictionaryModel> optional = repository.findById(model.getId());
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
    public DictionaryEntity update(DictionaryModel m) {
        final DictionaryRepository repository = P6eSpringUtil.getBean(DictionaryRepository.class);
        final Optional<DictionaryModel> optional = repository.findById(model.getId());
        if (optional.isPresent()) {
            m.setId(null); // 删除 ID 让其自增
            m.setUpdateDate(LocalDateTime.now()); // 设置更新时间
            return new DictionaryEntity(repository.saveAndFlush(P6eCopyUtil.run(m, optional.get())));
        } else {
            throw new P6eResourceNoExistException(this.getClass().getName() + " update< " + model.getId() + " >.");
        }
    }

}
