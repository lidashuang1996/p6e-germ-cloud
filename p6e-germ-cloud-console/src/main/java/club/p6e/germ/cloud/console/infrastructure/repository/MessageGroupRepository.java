package club.p6e.germ.cloud.console.infrastructure.repository;

import club.p6e.germ.cloud.console.infrastructure.model.MessageGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessageGroupRepository
        extends JpaRepository<MessageGroupModel, Integer>, JpaSpecificationExecutor<MessageGroupModel> {
}
