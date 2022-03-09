package club.p6e.germ.cloud.console.domain.aggregate.dictionary;

import club.p6e.germ.cloud.console.infrastructure.model.DictionaryModel;
import club.p6e.germ.cloud.console.infrastructure.repository.DictionaryRepository;
import com.p6e.germ.common.utils.P6eSpringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class DictionaryAggregate {

    private final String search;
    /** 搜索的类型 */
    private final String[] types;

    /** 搜索的语言 */
    private final String language;

    private final Integer page;
    private final Integer size;

    /** 查询结果 */
    private final Map<String, List<DictionaryModel>> result = new HashMap<>();

    /** 查询结果 */
    private long total = 0;
    private List<DictionaryModel> list = new ArrayList<>();

    /**
     * 构造函数初始化
     * 搜索条件初始化
     */
    public DictionaryAggregate(String[] types, String language) {
        this.types = types;
        this.search = null;
        this.language = language;
        this.page = -1;
        this.size = -1;
        this.executeType();
    }

    /**
     * 构造函数初始化
     * 搜索条件初始化
     */
    public DictionaryAggregate(String search, String language, Integer page, Integer size) {
        this.types = new String[0];
        this.search = search;
        this.language = language;
        this.page = page == null ? 1 : page;
        this.size = size == null ? 16 : size;
        this.executeSearch();
    }

    /**
     * 执行搜索操作
     */
    private void executeType () {
        final DictionaryRepository repository = P6eSpringUtil.getBean(DictionaryRepository.class);
        for (final String type : types) {
            result.put(type, repository.findAllByTypeAndLanguageAndIsDelete(type, language, 0));
        }
    }

    /**
     * 执行搜索操作
     */
    private void executeSearch () {
        final DictionaryRepository repository = P6eSpringUtil.getBean(DictionaryRepository.class);
        final Page<DictionaryModel> repositoryPage = repository.findAll(
                (Specification<DictionaryModel>) (root, criteriaQuery, criteriaBuilder) -> {
                    final List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get(DictionaryModel.IS_DELETE), 0));
                    predicates.add(criteriaBuilder.equal(root.get(DictionaryModel.LANGUAGE), language));
                    if (search != null) {
                        predicates.add(criteriaBuilder.like(root.get(DictionaryModel.TYPE), "%" + search + "%"));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, PageRequest.of(page - 1, size, Sort.by(Sort.Order.asc(DictionaryModel.ID))));
        this.total = repositoryPage.getTotalElements();
        this.list = new ArrayList<>(repositoryPage.getContent());
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

    public long getTotal() {
        return total;
    }

    public String getSearch() {
        return search;
    }

    public List<DictionaryModel> getList() {
        return list;
    }
}
