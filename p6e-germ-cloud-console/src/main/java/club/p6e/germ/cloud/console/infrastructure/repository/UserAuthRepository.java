package club.p6e.germ.cloud.console.infrastructure.repository;


import club.p6e.germ.cloud.console.infrastructure.model.UserAuthModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface UserAuthRepository extends JpaRepository<UserAuthModel, Integer> {
}
