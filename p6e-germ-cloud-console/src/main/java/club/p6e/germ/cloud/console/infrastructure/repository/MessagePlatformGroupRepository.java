package club.p6e.germ.cloud.console.infrastructure.repository;

import club.p6e.germ.cloud.console.infrastructure.model.MessagePlatformGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessagePlatformGroupRepository
        extends JpaRepository<MessagePlatformGroupModel, Integer>, JpaSpecificationExecutor<MessagePlatformGroupModel> {
}
