package club.p6e.germ.cloud.console.domain.entity.manage.user;

import club.p6e.germ.cloud.console.infrastructure.error.P6eResourceNoExistException;
import club.p6e.germ.cloud.console.infrastructure.model.UserModel;
import club.p6e.germ.cloud.console.infrastructure.repository.UserRepository;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eSpringUtil;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author lidashuang
 * @version 1.0
 */
public class UserManageEntity {

    private final UserModel model;

    public UserManageEntity(UserModel model) {
        this.model = model;
    }

    public UserManageEntity(Integer id) {
        final UserRepository repository = P6eSpringUtil.getBean(UserRepository.class);
        final Optional<UserModel> optional = repository.findById(id);
        if (optional.isPresent()) {
            this.model = optional.get();
        } else {
            throw new P6eResourceNoExistException(this.getClass() + "id <" + id + "> data no exist.");
        }
    }

    public UserManageEntity update(UserModel m) {
        final UserRepository repository = P6eSpringUtil.getBean(UserRepository.class);
        final Optional<UserModel> optional = repository.findById(model.getId());
        if (optional.isPresent()) {
            repository.save(P6eCopyUtil.run(optional.get(), m).setId(model.getId()).setUpdateDate(LocalDateTime.now()));
            return new UserManageEntity(model.getId());
        } else {
            throw new P6eResourceNoExistException(this.getClass() + "id <" + model.getId() + "> data no exist.");
        }
    }

    public UserManageEntity delete() {
        final UserRepository repository = P6eSpringUtil.getBean(UserRepository.class);
        repository.deleteById(model.getId());
        return this;
    }

    public UserModel getModel() {
        return model;
    }
}
