package club.p6e.germ.jurisdiction.repository;

import club.p6e.germ.jurisdiction.model.JurisdictionUrlGroupRelationUrlKeyModel;
import club.p6e.germ.jurisdiction.model.JurisdictionUrlGroupRelationUrlModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface JurisdictionUrlGroupRelationUrlRepository
        extends JpaRepository<JurisdictionUrlGroupRelationUrlModel, JurisdictionUrlGroupRelationUrlKeyModel> {
}
