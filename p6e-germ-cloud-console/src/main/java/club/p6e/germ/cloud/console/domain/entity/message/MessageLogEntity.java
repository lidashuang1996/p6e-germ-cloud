package club.p6e.germ.cloud.console.domain.entity.message;

import club.p6e.germ.cloud.console.infrastructure.model.MessageLogModel;
import club.p6e.germ.cloud.console.infrastructure.repository.MessageLogRepository;
import com.p6e.germ.common.utils.P6eSpringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public class MessageLogEntity {

    private List<MessageLogModel> collection = new ArrayList<>();

    public MessageLogEntity(MessageLogModel model) {
        this.init(model.getMark());
    }

    public MessageLogEntity(String mark) {
        this.init(mark);
    }


    private void init(String mark) {
        final MessageLogRepository repository = P6eSpringUtil.getBean(MessageLogRepository.class);
        final Page<MessageLogModel> repositoryPage = repository.findAll(
                (Specification<MessageLogModel>) (root, query, criteriaBuilder) ->
                        criteriaBuilder.equal(root.get(MessageLogModel.MARK), mark),
                PageRequest.of(0, 10)
        );
        this.collection = new ArrayList<>(repositoryPage.getContent());
    }

    public List<MessageLogModel> getCollection() {
        return collection;
    }
}
