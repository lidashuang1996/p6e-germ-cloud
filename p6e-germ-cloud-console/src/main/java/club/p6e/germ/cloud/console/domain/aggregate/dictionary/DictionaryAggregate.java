package club.p6e.germ.cloud.console.domain.aggregate.dictionary;

import club.p6e.germ.cloud.console.infrastructure.model.DictionaryModel;
import club.p6e.germ.cloud.console.infrastructure.repository.DictionaryRepository;
import com.p6e.germ.common.utils.P6eSpringUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class DictionaryAggregate {

    /** 搜索的类型 */
    private final String[] types;

    /** 搜索的语言 */
    private final String language;

    /** 查询结果 */
    private final Map<String, List<DictionaryModel>> result = new HashMap<>();

    /**
     * 构造函数初始化
     * 搜索条件初始化
     */
    public DictionaryAggregate(String[] types, String language) {
        this.types = types;
        this.language = language;
        this.execute();
    }

    /**
     * 执行搜索操作
     */
    private void execute () {
        final DictionaryRepository repository = P6eSpringUtil.getBean(DictionaryRepository.class);
        for (final String type : types) {
            result.put(type, repository.findAllByTypeAndLanguageAndIsDelete(type, language, 0));
        }
    }

    /**
     * 获取搜索内容
     * @return 搜索内容
     */
    public String[] getTypes() {
        return types;
    }

    public String getLanguage() {
        return language;
    }

    public Map<String, List<DictionaryModel>> getResult() {
        return result;
    }
}
