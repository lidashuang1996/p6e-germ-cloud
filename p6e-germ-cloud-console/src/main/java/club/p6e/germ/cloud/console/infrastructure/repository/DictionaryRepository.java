package club.p6e.germ.cloud.console.infrastructure.repository;

import club.p6e.germ.cloud.console.infrastructure.model.DictionaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface DictionaryRepository
        extends JpaRepository<DictionaryModel, Integer>, JpaSpecificationExecutor<DictionaryModel> {

    /**
     * 根据类型查询数据
     * @param type 类型
     * @param language 语言
     * @param isDelete 是否删除
     * @return 查询的结果
     */
    List<DictionaryModel> findAllByTypeAndLanguageAndIsDelete(String type, String language, Integer isDelete);

}
