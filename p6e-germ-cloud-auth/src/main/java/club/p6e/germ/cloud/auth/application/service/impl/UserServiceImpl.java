package club.p6e.germ.cloud.auth.application.service.impl;

import club.p6e.germ.cloud.auth.controller.support.model.UserModel;
import club.p6e.germ.cloud.auth.domain.module.sign.SignInTokenEntity;
import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import club.p6e.germ.cloud.auth.application.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserModel.ResultDto info(UserModel.ParamDto param) {
        final UserModel.ResultDto result = new UserModel.ResultDto();
        try {
            final SignInTokenEntity tokenEntity = SignInTokenEntity.accessToken(param.getAccessToken());
            result.setContent(tokenEntity.getUser());
        } catch (Exception e) {
            result.setError(ErrorModel.TOKEN_EXPIRE_EXCEPTION);
        }
        return result;
    }
}
