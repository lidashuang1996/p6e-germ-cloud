package club.p6e.germ.cloud.console.domain.aggregate.manage.user;

import club.p6e.germ.cloud.console.infrastructure.model.UserModel;
import club.p6e.germ.cloud.console.infrastructure.repository.UserRepository;
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
public class UserManageAggregate {

    private static final String ID_TYPE = "id";
    private static final String NAME_TYPE = "name";
    private static final String ACCOUNT_TYPE = "account";

    /** 搜索的内容 */
    private final String type;
    private final String search;
    /** 默认页码 */
    private final int page;
    /** 默认长度 */
    private final int size;

    /** 查询结果 */
    private long total = 0;
    private List<UserModel> list = new ArrayList<>();

    /**
     * 构造函数初始化
     * 搜索条件初始化
     */
    public UserManageAggregate() {
        this.page = 1;
        this.size = 16;
        this.type = null;
        this.search = null;
        this.execute();
    }

    /**
     * 构造函数初始化
     * 搜索条件初始化
     */
    public UserManageAggregate(Integer page, Integer size, String type, String search) {
        this.page = page == null || page <= 0 ? 1 : page;
        this.size = size == null || size <= 0 ? 16 : size;
        this.type = type;
        this.search = search;
        this.execute();
    }

    /**
     * 执行搜索操作
     */
    private void execute () {
        final UserRepository repository = P6eSpringUtil.getBean(UserRepository.class);
        final Page<UserModel> repositoryPage = repository.findAll(
                (Specification<UserModel>) (root, query, criteriaBuilder) -> {
                    final List<Predicate> predicates = new ArrayList<>();
                    final Predicate isDeletePredicate = criteriaBuilder.equal(root.get(UserModel.IS_DELETE), 0);
                    if (type == null || type.equalsIgnoreCase(ID_TYPE)) {
                        predicates.add(criteriaBuilder.like(root.get(UserModel.ID), "%" + search + "%"));
                    }
                    if (type == null || type.equalsIgnoreCase(NAME_TYPE)) {
                        predicates.add(criteriaBuilder.like(root.get(UserModel.NAME), "%" + search + "%"));
                        predicates.add(criteriaBuilder.like(root.get(UserModel.NICKNAME), "%" + search + "%"));
                    }
                    if (type == null || type.equalsIgnoreCase(ACCOUNT_TYPE)) {
                        predicates.add(criteriaBuilder.like(root.get(UserModel.AUTH).get(UserModel.AUTH_EMAIL), "%" + search + "%"));
                        predicates.add(criteriaBuilder.like(root.get(UserModel.AUTH).get(UserModel.AUTH_PHONE), "%" + search + "%"));
                    }
                    return search == null ? isDeletePredicate : criteriaBuilder.and(
                            isDeletePredicate,
                            criteriaBuilder.or(predicates.toArray(new Predicate[0]))
                    );
                },
                PageRequest.of(page - 1, size, Sort.by(Sort.Order.asc(UserModel.ID)))
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

    public List<UserModel> getList() {
        return list;
    }

}
