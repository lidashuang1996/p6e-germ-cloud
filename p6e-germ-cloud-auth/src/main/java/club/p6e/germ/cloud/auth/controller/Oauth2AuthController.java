package club.p6e.germ.cloud.auth.controller;

import club.p6e.germ.cloud.auth.P6eCloudAuthProperties;
import club.p6e.germ.cloud.auth.application.service.Oauth2AuthService;
import club.p6e.germ.cloud.auth.controller.support.ApiResultModel;
import club.p6e.germ.cloud.auth.controller.support.model.Oauth2Model;
import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import club.p6e.germ.cloud.auth.controller.support.BaseController;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eResourceUtil;
import com.p6e.germ.common.utils.P6eTransformUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/auth")
public class Oauth2AuthController extends BaseController {

    /** 注入认证服务 */
    @Resource
    private Oauth2AuthService oauth2AuthService;

    /** 登录页面 */
    private final String loginPage;
    private final Map<String, String> loginPageContent;

    /**
     * 注入 Oauth2 Type 数据
     */
    private final List<P6eCloudAuthProperties.Oauth2Type> oauth2Types;

    /**
     * 初始化页面配置文件内容
     */
    @Autowired
    public Oauth2AuthController(P6eCloudAuthProperties properties) {
        try {
            this.oauth2Types = Arrays.asList(properties.getOauth2Types());
            this.loginPageContent = properties.getLogin().getContent();
            this.loginPage = P6eResourceUtil.getResourceContent(properties.getLogin().getFile());
            LOGGER.info("PAGE --> " + loginPage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ApiResultModel def(HttpServletResponse response) {
        // 读取参数
        final String responseType = getParameter(RESPONSE_TYPE_PARAM1, RESPONSE_TYPE_PARAM2);
        final String clientId = getParameter(CLIENT_ID_PARAM1, CLIENT_ID_PARAM2);
        final String redirectUri = getParameter(REDIRECT_URI_PARAM1, REDIRECT_URI_PARAM2);
        final String scope = getParameter(SCOPE_TYPE_PARAM);
        final String state = getParameter(STATE_TYPE_PARAM);
        if (responseType == null || clientId == null || redirectUri == null || scope == null) {
            return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            // 验证类型
            if (!OAUTH2_RESPONSE_TYPE.equalsIgnoreCase(responseType)) {
                return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
            }
            // 验证是否开启服务
            if (!oauth2Types.contains(P6eCloudAuthProperties.Oauth2Type.CODE)) {
                return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
            }
            // 验证参数成功
            final Oauth2Model.Code.ParamDto codeParam = new Oauth2Model.Code.ParamDto();
            codeParam.setScope(scope);
            codeParam.setState(state);
            codeParam.setClientId(clientId);
            codeParam.setRedirectUri(redirectUri);
            // 验证客户端的参数，并获取客户端的数据
            final Oauth2Model.Code.ResultDto codeResult = oauth2AuthService.code(codeParam);
            if (codeResult.getError() != null) {
                return ApiResultModel.build(codeResult.getError());
            } else {
                // 写入登录特定的数据
                final Map<String, String> content = new HashMap<>(loginPageContent.size() + 2);
                for (final String key : loginPageContent.keySet()) {
                    content.put(key, loginPageContent.get(key));
                }
                content.put("__PAGE__", "LOGIN");
                content.put("__VOUCHER__", codeResult.getVoucher());
                PrintWriter pw = null;
                try {
                    response.addHeader(CONTENT_TYPE, HTML_CONTENT_TYPE);
                    pw = response.getWriter();
                    pw.write(P6eTransformUtil.template(loginPage, content));
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (pw != null) {
                        pw.flush();
                        pw.close();
                    }
                }
                return null;
            }
        }
    }

    @GetMapping("/confirm")
    public ApiResultModel confirm(Oauth2Model.Confirm.ParamVo param) {
        if (param == null || param.getVoucher() == null) {
            return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            // 验证是否开启服务
            if (!oauth2Types.contains(P6eCloudAuthProperties.Oauth2Type.CODE)) {
                return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
            }
            // 执行二次确认的服务
            final Oauth2Model.Confirm.ResultDto codeResult =
                    oauth2AuthService.confirm(P6eCopyUtil.run(param, Oauth2Model.Confirm.ParamDto.class));
            if (codeResult.getError() != null) {
                return ApiResultModel.build(codeResult.getError());
            } else {
                return ApiResultModel.build(codeResult);
            }
        }
    }
}
