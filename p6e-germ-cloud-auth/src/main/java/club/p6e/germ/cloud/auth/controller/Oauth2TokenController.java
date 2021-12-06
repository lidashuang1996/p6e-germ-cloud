package club.p6e.germ.cloud.auth.controller;

import club.p6e.germ.cloud.auth.P6eCloudAuthProperties;
import club.p6e.germ.cloud.auth.application.service.Oauth2TokenService;
import club.p6e.germ.cloud.auth.controller.support.ApiResultModel;
import club.p6e.germ.cloud.auth.controller.support.model.Oauth2Model;
import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import club.p6e.germ.cloud.auth.controller.support.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/token")
public class Oauth2TokenController extends BaseController {

    /** 注入服务 */
    @Resource
    private Oauth2TokenService oauth2TokenService;

    /**
     * 注入 Oauth2 Type 数据
     */
    private final List<P6eCloudAuthProperties.Oauth2Type> oauth2Types;

    /**
     * 构造方法注入配置文件对象
     * @param properties 配置文件对象
     */
    @Autowired
    public Oauth2TokenController(P6eCloudAuthProperties properties) {
        this.oauth2Types = Arrays.asList(properties.getOauth2Types());
    }

    @GetMapping
    public ApiResultModel def() {
        final String grantType = getParameter(GRANT_TYPE_PARAM1, GRANT_TYPE_PARAM2);
        final String clientId = getParameter(CLIENT_ID_PARAM1, CLIENT_ID_PARAM2);
        final String clientSecret = getParameter(CLIENT_SECRET_PARAM1, CLIENT_SECRET_PARAM2);
        if (grantType != null && clientId != null && clientSecret != null) {
            // 客户端模式
            if (grantType.equalsIgnoreCase(OAUTH2_GRANT_CLIENT_TYPE)) {
                if (oauth2Types.contains(P6eCloudAuthProperties.Oauth2Type.CLIENT)) {
                    final Oauth2Model.Client.ParamDto param = new Oauth2Model.Client.ParamDto();
                    param.setClientId(clientId);
                    param.setClientId(clientSecret);
                    return ApiResultModel.build(oauth2TokenService.client(param));
                } else {
                    return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
                }
            }
            // 密码模式
            if (grantType.equalsIgnoreCase(OAUTH2_GRANT_PASSWORD_TYPE)) {
                if (oauth2Types.contains(P6eCloudAuthProperties.Oauth2Type.PASSWORD)) {
                    final String scope = getParameter(SCOPE_TYPE_PARAM);
                    final String username = getParameter(USERNAME_TYPE_PARAM);
                    final String password = getParameter(PASSWORD_TYPE_PARAM);
                    if (scope == null || username == null || password == null) {
                        return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
                    } else {
                        final Oauth2Model.Password.ParamDto param = new Oauth2Model.Password.ParamDto();
                        param.setClientId(clientId);
                        param.setUsername(username);
                        param.setPassword(password);
                        return ApiResultModel.build(oauth2TokenService.password(param));
                    }
                } else {
                    return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
                }
            }
            // CODE 模式的回调
            if (grantType.equalsIgnoreCase(OAUTH2_GRANT_AUTH_CODE_TYPE)) {
                if (oauth2Types.contains(P6eCloudAuthProperties.Oauth2Type.CODE)) {
                    final String code = getParameter(CODE_PARAM);
                    final String redirectUri = getParameter(REDIRECT_URI_PARAM1, REDIRECT_URI_PARAM2);
                    if (code == null || redirectUri == null) {
                        return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
                    } else {
                        final Oauth2Model.AuthCode.ParamDto param = new Oauth2Model.AuthCode.ParamDto();
                        param.setCode(code);
                        param.setClientId(clientId);
                        param.setClientSecret(clientSecret);
                        param.setRedirectUri(redirectUri);
                        return ApiResultModel.build(oauth2TokenService.authCode(param));
                    }
                } else {
                    return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
                }
            }
            return ApiResultModel.build(ErrorModel.CLIENT_RESPONSE_TYPE_EXCEPTION);
        }
        return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
    }
}
