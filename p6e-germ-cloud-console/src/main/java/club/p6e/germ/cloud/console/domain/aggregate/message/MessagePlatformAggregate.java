package club.p6e.germ.cloud.console.domain.aggregate.message;

import club.p6e.germ.cloud.console.infrastructure.model.MessagePlatformModel;
import club.p6e.germ.cloud.console.infrastructure.repository.MessagePlatformRepository;
import com.p6e.germ.common.utils.P6eSpringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MessagePlatformAggregate {

    /** 搜索的内容 */
    private final String search;
    /** 搜索的类型 */
    private final String type;
    private final Integer status;
    /** 开始时间 */
    private final String startDateTime;
    /** 结束时间 */
    private final String endDateTime;
    /** 默认页码 */
    private final int page;
    /** 默认长度 */
    private final int size;

    /** 查询结果 */
    private long total = 0;
    private List<MessagePlatformModel> list = new ArrayList<>();

    public MessagePlatformAggregate() {
        this.page = 1;
        this.size = 16;
        this.search = null;
        this.type = null;
        this.status = null;
        this.startDateTime = null;
        this.endDateTime = null;
        this.execute();
    }

    public MessagePlatformAggregate(String search, String type, Integer status, Integer page, Integer size) {
        this.page = page == null ? 1 : page;
        this.size = size == null ? 16 : size;
        this.search = search;
        this.type = type;
        this.status = status;
        this.startDateTime = null;
        this.endDateTime = null;
        this.execute();
    }

    public MessagePlatformAggregate(String search, String type, String startDateTime, String endDateTime, int page, int size) {
        this.search = search;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.page = page;
        this.size = size;
        this.status = null;
        this.execute();
    }

    /**
     * 执行搜索操作
     */
    private void execute () {
        final MessagePlatformRepository repository = P6eSpringUtil.getBean(MessagePlatformRepository.class);
        final Page<MessagePlatformModel> repositoryPage = repository.findAll(
                (Specification<MessagePlatformModel>) (root, query, criteriaBuilder) -> {
                    final List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.get(MessagePlatformModel.IS_DELETE), 0));
                    if (type != null) {
                        predicates.add(criteriaBuilder.equal(root.get(MessagePlatformModel.TYPE), type));
                    }
                    if (status != null) {
                        predicates.add(criteriaBuilder.equal(root.get(MessagePlatformModel.STATUS), status));
                    }
                    if (search != null) {
                        predicates.add(criteriaBuilder.or(
                                criteriaBuilder.like(root.get(MessagePlatformModel.NAME), "%" + search + "%"),
                                criteriaBuilder.like(root.get(MessagePlatformModel.DESCRIBE), "%" + search + "%")
                        ));
                    }
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                },
                PageRequest.of(page - 1, size, Sort.by(Sort.Order.asc(MessagePlatformModel.ID)))
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

    public String getType() {
        return type;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
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

    public List<MessagePlatformModel> getList() {
        return list;
    }
}
