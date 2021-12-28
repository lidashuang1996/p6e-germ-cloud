package club.p6e.germ.cloud.console.infrastructure.repository;

import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUrlGroupRelationUrlKeyModel;
import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUrlGroupRelationUrlModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface JurisdictionUrlGroupRelationUrlRepository
        extends JpaRepository<JurisdictionUrlGroupRelationUrlModel, JurisdictionUrlGroupRelationUrlKeyModel> {
}
