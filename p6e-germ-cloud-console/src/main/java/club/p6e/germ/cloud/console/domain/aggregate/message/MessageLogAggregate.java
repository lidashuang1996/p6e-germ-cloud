package club.p6e.germ.cloud.console.domain.aggregate.message;

import club.p6e.germ.cloud.console.infrastructure.model.MessageLogModel;
import club.p6e.germ.cloud.console.infrastructure.repository.MessageLogRepository;
import com.p6e.germ.common.utils.P6eSpringUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MessageLogAggregate {

    /** 搜索的内容 */
    private final String search;
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
    private List<MessageLogModel> list = new ArrayList<>();

    public MessageLogAggregate() {
        this.page = 1;
        this.size = 16;
        this.search = null;
        this.startDateTime = null;
        this.endDateTime = null;
        this.execute();
    }

    public MessageLogAggregate(String search, Integer page, Integer size) {
        this.page = page == null ? 1 : page;
        this.size = size == null ? 16 : size;
        this.search = search;
        this.startDateTime = null;
        this.endDateTime = null;
        this.execute();
    }

    public MessageLogAggregate(String search, String type, String startDateTime, String endDateTime, int page, int size) {
        this.search = search;
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
        final String search = this.search == null ? "" : this.search;
        final MessageLogRepository repository = P6eSpringUtil.getBean(MessageLogRepository.class);
        final long repositoryCount = repository.countGroupByMarkAndPidAndTidAndSource(("%" + search + "%"));
        final List<MessageLogModel> repositoryContent = repository.selectGroupByMarkAndPidAndTidAndSource(("%" + search + "%"),
                PageRequest.of(page - 1, size, Sort.by(Sort.Order.desc(MessageLogModel.MARK))));
        this.total = repositoryCount;
        this.list = new ArrayList<>(repositoryContent);
    }

    /**
     * 获取搜索内容
     * @return 搜索内容
     */
    public String getSearch() {
        return search;
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

    public List<MessageLogModel> getList() {
        return list;
    }

}
