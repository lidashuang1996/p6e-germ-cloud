package club.p6e.germ.cloud.console.infrastructure.repository;

import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUserRelationUrlGroupKeyModel;
import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUserRelationUrlGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface JurisdictionUserRelationUrlGroupRepository
        extends JpaRepository<JurisdictionUserRelationUrlGroupModel, JurisdictionUserRelationUrlGroupKeyModel> {

    List<JurisdictionUserRelationUrlGroupModel> findAllByUid(Integer uid);

}
