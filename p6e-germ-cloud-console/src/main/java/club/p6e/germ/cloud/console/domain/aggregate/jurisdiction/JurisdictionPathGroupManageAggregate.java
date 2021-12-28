package club.p6e.germ.cloud.console.domain.aggregate.jurisdiction;

import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUrlGroupModel;
import club.p6e.germ.cloud.console.infrastructure.repository.JurisdictionUrlGroupRepository;
import com.p6e.germ.common.utils.P6eSpringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础管理/权限管理/权限组
 * @author lidashuang
 * @version 1.0
 */
public class JurisdictionPathGroupManageAggregate {

    /** 搜索的内容 */
    private final String search;
    /** 默认页码 */
    private final int page;
    /** 默认长度 */
    private final int size;

    /** 查询结果 */
    private long total = 0;
    private List<JurisdictionUrlGroupModel> list = new ArrayList<>();

    /**
     * 构造函数初始化
     * 搜索条件初始化
     */
    public JurisdictionPathGroupManageAggregate() {
        this.page = 1;
        this.size = 16;
        this.search = null;
        this.execute();
    }

    /**
     * 构造函数初始化
     * 搜索条件初始化
     */
    public JurisdictionPathGroupManageAggregate(Integer page, Integer size, String search) {
        this.page = page == null || page <= 0 ? 1 : page;
        this.size = size == null || size <= 0 ? 16 : size;
        this.search = search;
        this.execute();
    }

    /**
     * 执行搜索操作
     */
    private void execute () {
        final JurisdictionUrlGroupRepository repository = P6eSpringUtil.getBean(JurisdictionUrlGroupRepository.class);
        final Page<JurisdictionUrlGroupModel> repositoryPage = repository.findAll(
                (Specification<JurisdictionUrlGroupModel>) (root, query, criteriaBuilder) -> {
                    final Predicate predicate = criteriaBuilder.equal(root.get(JurisdictionUrlGroupModel.IS_DELETE), 0);
                    return search == null ? predicate : criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.or(
                                    criteriaBuilder.like(root.get(JurisdictionUrlGroupModel.ID), "%" + search + "%"),
                                    criteriaBuilder.like(root.get(JurisdictionUrlGroupModel.NAME), "%" + search + "%"),
                                    criteriaBuilder.like(root.get(JurisdictionUrlGroupModel.DESCRIBE), "%" + search + "%")
                            )
                    );
                },
                PageRequest.of(page - 1, size, Sort.by(Sort.Order.asc(JurisdictionUrlGroupModel.ID)))
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

    public List<JurisdictionUrlGroupModel> getList() {
        return list;
    }
}
