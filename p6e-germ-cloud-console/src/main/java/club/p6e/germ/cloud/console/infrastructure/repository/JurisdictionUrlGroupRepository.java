package club.p6e.germ.cloud.console.infrastructure.repository;

import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUrlGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface JurisdictionUrlGroupRepository
        extends JpaRepository<JurisdictionUrlGroupModel, Integer>, JpaSpecificationExecutor<JurisdictionUrlGroupModel> {

}
