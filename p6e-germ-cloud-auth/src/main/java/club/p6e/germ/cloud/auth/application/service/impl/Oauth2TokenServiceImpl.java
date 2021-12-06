package club.p6e.germ.cloud.auth.application.service.impl;

import club.p6e.germ.cloud.auth.application.service.Oauth2TokenService;
import club.p6e.germ.cloud.auth.controller.support.model.Oauth2Model;
import club.p6e.germ.cloud.auth.domain.keyvalue.*;
import club.p6e.germ.cloud.auth.domain.module.oauth2.Oauth2ClientEntity;
import club.p6e.germ.cloud.auth.domain.module.oauth2.Oauth2CodeEntity;
import club.p6e.germ.cloud.auth.domain.module.oauth2.Oauth2TokenEntity;
import club.p6e.germ.cloud.auth.domain.module.sign.SignInAccountPasswordEntity;
import club.p6e.germ.cloud.auth.domain.module.sign.SignInTokenEntity;
import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import com.p6e.germ.common.utils.P6eFormatUtil;
import org.springframework.stereotype.Service;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class Oauth2TokenServiceImpl implements Oauth2TokenService {

    @Override
    public Oauth2Model.Client.ResultDto client(Oauth2Model.Client.ParamDto param) {
        final Oauth2ClientEntity clientEntity;
        final Oauth2Model.Client.ResultDto result = new Oauth2Model.Client.ResultDto();
        try {
            clientEntity = new Oauth2ClientEntity(param.getClientId());
        } catch (Exception e) {
            // 忽略
            result.setError(ErrorModel.CLIENT_ACCOUNT_OR_PASSWORD_EXCEPTION);
            return result;
        }
        // 验证客户端密码
        if (clientEntity.verifySecret(param.getClientSecret())) {
            final Oauth2TokenEntity oauth2TokenEntity = Oauth2TokenEntity.generate(clientEntity.getClient());
            final TokenKeyValue tokenKeyValue = oauth2TokenEntity.getData();
            result.setScope(clientEntity.getScope());
            result.setTokenType(Oauth2TokenEntity.TOKEN_TYPE);
            result.setExpiresIn(Oauth2TokenEntity.EXPIRES_IN);
            result.setAccessToken(tokenKeyValue.getAccessToken());
            result.setRefreshToken(tokenKeyValue.getRefreshToken());
        } else {
            result.setError(ErrorModel.CLIENT_ACCOUNT_OR_PASSWORD_EXCEPTION);
        }
        return result;
    }

    @Override
    public Oauth2Model.Password.ResultDto password(Oauth2Model.Password.ParamDto param) {
        final Oauth2ClientEntity clientEntity;
        final Oauth2Model.Password.ResultDto result = new Oauth2Model.Password.ResultDto();
        try {
            clientEntity = new Oauth2ClientEntity(param.getClientId());
        } catch (Exception e) {
            // 忽略
            result.setError(ErrorModel.CLIENT_ACCOUNT_OR_PASSWORD_EXCEPTION);
            return result;
        }
        // 验证客户端密码
        if (clientEntity.verifySecret(param.getClientSecret())) {
            // 验证作用域
            if (clientEntity.verifyScope(param.getScope())) {
                final SignInAccountPasswordEntity accountPasswordEntity;
                // 下面验证用户的账号密码
                try {
                    final String username = param.getUsername();
                    final boolean isEmail = P6eFormatUtil.emailVerification(username);
                    final AccountKeyValue account = new AccountKeyValue(username, isEmail ? "email" : "phone");
                    accountPasswordEntity = new SignInAccountPasswordEntity(account);
                } catch (Exception e) {
                    // 忽略
                    result.setError(ErrorModel.ACCOUNT_OR_PASSWORD_EXCEPTION);
                    return result;
                }
                if (accountPasswordEntity.verifyPassword(new PasswordKeyValue(param.getPassword()))) {
                    final TokenKeyValue tokenKeyValue = SignInTokenEntity.generate(
                            accountPasswordEntity.getUser(), new DeviceKeyValue("OAUTH2_CLIENT")).getData();
                    result.setScope(param.getScope());
                    result.setExpiresIn(SignInTokenEntity.EXPIRES_IN);
                    result.setTokenType(SignInTokenEntity.TOKEN_TYPE);
                    result.setAccessToken(tokenKeyValue.getAccessToken());
                    result.setRefreshToken(tokenKeyValue.getRefreshToken());
                } else {
                    result.setError(ErrorModel.ACCOUNT_OR_PASSWORD_EXCEPTION);
                }
            } else {
                result.setError(ErrorModel.CLIENT_SCOPE_PARAMETER_EXCEPTION);
            }
        } else {
            result.setError(ErrorModel.CLIENT_ACCOUNT_OR_PASSWORD_EXCEPTION);
        }
        return result;
    }

    @Override
    public Oauth2Model.AuthCode.ResultDto authCode(Oauth2Model.AuthCode.ParamDto param) {
        final Oauth2ClientEntity clientEntity;
        final Oauth2Model.AuthCode.ResultDto result = new Oauth2Model.AuthCode.ResultDto();
        try {
            clientEntity = new Oauth2ClientEntity(param.getClientId());
        } catch (Exception e) {
            // 忽略
            result.setError(ErrorModel.CLIENT_ACCOUNT_OR_PASSWORD_EXCEPTION);
            return result;
        }
        // 验证客户端密码
        if (clientEntity.verifySecret(param.getClientSecret())) {
            // 验证重定向的 URI
            if (clientEntity.verifyRedirectUri(param.getRedirectUri())) {
                final Oauth2CodeEntity oauth2CodeEntity;
                try {
                    oauth2CodeEntity = new Oauth2CodeEntity(new CodeKeyValue(param.getCode()));
                } catch (Exception e) {
                    result.setError(ErrorModel.CLIENT_AUTH_CODE_NOT_EXIST_EXCEPTION);
                    return result;
                }
                // 读取认证 CODE 实体里面的用户数据
                final TokenKeyValue tokenKeyValue = oauth2CodeEntity.getData();
                result.setScope(oauth2CodeEntity.getScope());
                result.setTokenType(Oauth2TokenEntity.TOKEN_TYPE);
                result.setExpiresIn(Oauth2TokenEntity.EXPIRES_IN);
                result.setAccessToken(tokenKeyValue.getAccessToken());
                result.setRefreshToken(tokenKeyValue.getRefreshToken());
            } else {
                result.setError(ErrorModel.CLIENT_REDIRECT_URI_PARAMETER_EXCEPTION);
            }
        } else {
            result.setError(ErrorModel.CLIENT_ACCOUNT_OR_PASSWORD_EXCEPTION);
        }
        return result;
    }
}
