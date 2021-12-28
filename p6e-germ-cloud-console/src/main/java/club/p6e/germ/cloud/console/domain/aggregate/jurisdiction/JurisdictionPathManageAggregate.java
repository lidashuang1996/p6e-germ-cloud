package club.p6e.germ.cloud.console.domain.aggregate.jurisdiction;

import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUrlGroupModel;
import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUrlModel;
import club.p6e.germ.cloud.console.infrastructure.repository.JurisdictionUrlRepository;
import com.p6e.germ.common.utils.P6eSpringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lidashuang
 * @version 1.0
 */
public class JurisdictionPathManageAggregate {

    /** 搜索的内容 */
    private final String search;
    /** 默认页码 */
    private final int page;
    /** 默认长度 */
    private final int size;

    /** 查询结果 */
    private long total = 0;
    private List<JurisdictionUrlModel> list = new ArrayList<>();

    /**
     * 构造函数初始化
     * 搜索条件初始化
     */
    public JurisdictionPathManageAggregate() {
        this.search = "";
        this.page = 1;
        this.size = 16;
        this.execute();
    }

    /**
     * 构造函数初始化
     * 搜索条件初始化
     */
    public JurisdictionPathManageAggregate(Integer page, Integer size, String search) {
        this.page = page == null || page <= 0 ? 1 : page;
        this.size = size == null || size <= 0 ? 16 : size;
        this.search = search;
        this.execute();
    }

    /**
     * 执行搜索操作
     */
    private void execute () {
        final JurisdictionUrlRepository repository = P6eSpringUtil.getBean(JurisdictionUrlRepository.class);
        final Page<JurisdictionUrlModel> repositoryPage = repository.findAll(
                (Specification<JurisdictionUrlModel>) (root, query, criteriaBuilder) -> {
                    final Predicate predicate = criteriaBuilder.equal(root.get(JurisdictionUrlGroupModel.IS_DELETE), 0);
                    return search == null ? predicate : criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.or(
                                criteriaBuilder.like(root.get(JurisdictionUrlModel.ID), "%" + search + "%"),
                                criteriaBuilder.like(criteriaBuilder.concat(
                                        root.get(JurisdictionUrlModel.BASE_URL),
                                        root.get(JurisdictionUrlModel.URL)
                                ), "%" + search + "%"),
                                criteriaBuilder.like(root.get(JurisdictionUrlModel.NAME), "%" + search + "%"),
                                criteriaBuilder.like(root.get(JurisdictionUrlModel.DESCRIBE), "%" + search + "%")
                            )
                    );
                },
                PageRequest.of(page - 1, size, Sort.by(Sort.Order.asc(JurisdictionUrlModel.ID)))
        );
        this.total = repositoryPage.getTotalElements();
        this.list = new ArrayList<>(repositoryPage.getContent());
    }

    /**
     * 获取搜索内容
     * @return 搜索内容
     */
    public String getSearch() {
        return search;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }

    public List<JurisdictionUrlModel> getList() {
        return list;
    }

}
