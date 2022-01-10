package club.p6e.germ.cloud.console.infrastructure.repository;

import club.p6e.germ.cloud.console.infrastructure.model.MessagePlatformModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface MessagePlatformRepository
        extends JpaRepository<MessagePlatformModel, Integer>, JpaSpecificationExecutor<MessagePlatformModel> {
}
