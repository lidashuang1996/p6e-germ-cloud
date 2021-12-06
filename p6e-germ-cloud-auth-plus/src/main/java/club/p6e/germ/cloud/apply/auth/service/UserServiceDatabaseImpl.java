package club.p6e.germ.cloud.apply.auth.service;

import club.p6e.germ.cloud.apply.auth.model.UserAuthModel;
import club.p6e.germ.cloud.apply.auth.model.UserModel;
import club.p6e.germ.cloud.apply.auth.repository.UserAuthRepository;
import club.p6e.germ.cloud.apply.auth.repository.UserRepository;
import club.p6e.germ.cloud.auth.domain.keyvalue.UserKeyValue;
import club.p6e.germ.cloud.auth.domain.service.UserService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Optional;

/**
 * 实现对用户的相关操作
 * @author lidashuang
 * @version 1.0
 */
@Component
public class UserServiceDatabaseImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserAuthRepository userAuthRepository;

    @Override
    public UserKeyValue findById(Integer id) {
        final Optional<UserModel> optional = userRepository.findByIdAndIsDelete(id, 0);
        return optional.map(this::modelToUserKeyValue).orElse(null);
    }

    @Override
    public UserKeyValue findByAccount(String account) {
        final Optional<UserModel> optional = userRepository.findOne(
                (Specification<UserModel>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.or(
                        criteriaBuilder.equal(root.get("auth").get("email"), account),
                        criteriaBuilder.equal(root.get("auth").get("phone"), account)));
        return optional.map(this::modelToUserKeyValue).orElse(null);
    }

    @Override
    public UserKeyValue create(String account, String code, String type) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserKeyValue create(String account, String password) {
        final UserModel userModel = new UserModel();
        userModel.setName(account);
        userModel.setNickname(account);
        userRepository.saveAndFlush(userModel);
        final UserAuthModel userAuthModel = new UserAuthModel();
        userAuthModel.setId(userModel.getId());
        userAuthModel.setEmail(account);
        userAuthModel.setPassword(password);
        userAuthRepository.saveAndFlush(userAuthModel);
        return this.findById(userModel.getId());
    }

    @Override
    public UserKeyValue updatePassword(String account, String password) {
        final UserKeyValue userKeyValue = this.findByAccount(account);
        if (userKeyValue == null) {
            return null;
        } else {
            final UserAuthModel userAuthModel = new UserAuthModel();
            userAuthModel.setId(Integer.valueOf(userKeyValue.getId()));
            userAuthModel.setPassword(password);
            userAuthRepository.saveAndFlush(userAuthModel);
            return this.findById(userAuthModel.getId());
        }
    }

    private static String objectToString(Object o) {
        if (o == null) {
            return "";
        } else {
            return String.valueOf(o);
        }
    }

    private UserKeyValue modelToUserKeyValue(UserModel userModel) {
        final UserKeyValue userKeyValue = new UserKeyValue();
        userKeyValue.setId(objectToString(userModel.getId()));
        userKeyValue.setAccount(userModel.getAuth().getEmail());
        userKeyValue.setPassword(userModel.getAuth().getPassword());
        userKeyValue.put("status", objectToString(userModel.getStatus()));
        userKeyValue.put("name", objectToString(userModel.getName()));
        userKeyValue.put("nickname", objectToString(userModel.getNickname()));
        userKeyValue.put("avatar", objectToString(userModel.getAvatar()));
        userKeyValue.put("describe", objectToString(userModel.getDescribe()));
        userKeyValue.put("age", objectToString(userModel.getAge()));
        userKeyValue.put("sex", objectToString(userModel.getSex()));
        userKeyValue.put("email", objectToString(userModel.getAuth().getEmail()));
        userKeyValue.put("phone", objectToString(userModel.getAuth().getPhone()));
        userKeyValue.put("$password", objectToString(userModel.getAuth().getPassword()));
        userKeyValue.put("$qq", objectToString(userModel.getAuth().getQq()));
        userKeyValue.put("$wechat", objectToString(userModel.getAuth().getWechat()));
        return userKeyValue;
    }
}
