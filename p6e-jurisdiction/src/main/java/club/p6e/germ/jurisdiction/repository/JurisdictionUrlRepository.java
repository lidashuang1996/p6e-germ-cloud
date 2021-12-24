package club.p6e.germ.jurisdiction.repository;

import club.p6e.germ.jurisdiction.model.JurisdictionUrlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface JurisdictionUrlRepository
        extends JpaRepository<JurisdictionUrlModel, Integer>, JpaSpecificationExecutor<JurisdictionUrlModel> {
}
