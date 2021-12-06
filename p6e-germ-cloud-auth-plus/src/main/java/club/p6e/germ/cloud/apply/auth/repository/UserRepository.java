package club.p6e.germ.cloud.apply.auth.repository;

import club.p6e.germ.cloud.apply.auth.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<UserModel, Integer>, JpaSpecificationExecutor<UserModel> {

    Optional<UserModel> findByIdAndIsDelete(Integer id, Integer isDelete);
}
