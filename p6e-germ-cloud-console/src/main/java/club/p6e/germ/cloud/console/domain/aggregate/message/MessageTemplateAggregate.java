package club.p6e.germ.cloud.console.domain.aggregate.message;

import club.p6e.germ.cloud.console.infrastructure.model.MessageTemplateModel;
import club.p6e.germ.cloud.console.infrastructure.repository.MessageTemplateRepository;
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
public class MessageTemplateAggregate {

    /** 搜索的内容 */
    private final String search;
    /** 搜索的类型 */
    private final String type;
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
    private List<MessageTemplateModel> list = new ArrayList<>();

    public MessageTemplateAggregate() {
        this.page = 1;
        this.size = 16;
        this.search = null;
        this.type = null;
        this.startDateTime = null;
        this.endDateTime = null;
        this.execute();
    }

    public MessageTemplateAggregate(int page, int size) {
        this.page = page;
        this.size = size;
        this.search = null;
        this.type = null;
        this.startDateTime = null;
        this.endDateTime = null;
        this.execute();
    }

    public MessageTemplateAggregate(String search, String type, String startDateTime, String endDateTime, int page, int size) {
        this.search = search;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.page = page;
        this.size = size;
        this.execute();
    }

    /**
     * 执行搜索操作
     */
    private void execute () {
        final MessageTemplateRepository repository = P6eSpringUtil.getBean(MessageTemplateRepository.class);
        final Page<MessageTemplateModel> repositoryPage = repository.findAll(
                (Specification<MessageTemplateModel>) (root, query, criteriaBuilder) -> {
                    final Predicate predicate = criteriaBuilder.equal(root.get(MessageTemplateModel.IS_DELETE), 0);
                    return search == null ? predicate : criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.or(
                                    criteriaBuilder.like(root.get(MessageTemplateModel.NAME), "%" + search + "%")
                            )
                    );
                },
                PageRequest.of(page - 1, size, Sort.by(Sort.Order.asc(MessageTemplateModel.ID)))
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

    public List<MessageTemplateModel> getList() {
        return list;
    }
}
