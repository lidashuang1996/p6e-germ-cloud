package club.p6e.germ.jurisdiction.repository;

import club.p6e.germ.jurisdiction.model.JurisdictionUserRelationUrlGroupKeyModel;
import club.p6e.germ.jurisdiction.model.JurisdictionUserRelationUrlGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface JurisdictionUserRelationUrlGroupRepository
        extends JpaRepository<JurisdictionUserRelationUrlGroupModel, JurisdictionUserRelationUrlGroupKeyModel> {
}
