package club.p6e.germ.cloud.console.infrastructure.repository;

import club.p6e.germ.cloud.console.infrastructure.model.MessageLogModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageLogRepository
        extends JpaRepository<MessageLogModel, Integer>, JpaSpecificationExecutor<MessageLogModel> {


    @Query(
            nativeQuery = true,
            value = "select count(A.mark) from (select mark from p6e_message_log where mark like ?1 group by mark) AS A"
    )
    long countGroupByMarkAndPidAndTidAndSource(String mark);

    @Query(
            value = "select new club.p6e.germ.cloud.console.infrastructure.model.MessageLogModel(mark, pid, tid, source) from p6e_message_log where mark like ?1 group by mark, pid, tid, source"
    )
    List<MessageLogModel> selectGroupByMarkAndPidAndTidAndSource(String mark, PageRequest pageRequest);
}
