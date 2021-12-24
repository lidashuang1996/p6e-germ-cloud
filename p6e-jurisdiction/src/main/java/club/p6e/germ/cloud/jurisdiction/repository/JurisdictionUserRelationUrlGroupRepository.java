package club.p6e.germ.cloud.jurisdiction.repository;

import club.p6e.germ.cloud.jurisdiction.model.JurisdictionUserRelationUrlGroupKeyModel;
import club.p6e.germ.cloud.jurisdiction.model.JurisdictionUserRelationUrlGroupModel;
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
