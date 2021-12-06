package club.p6e.germ.cloud.auth.controller;

import club.p6e.germ.cloud.auth.P6eCloudAuthProperties;
import club.p6e.germ.cloud.auth.controller.support.ApiResultModel;
import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import club.p6e.germ.cloud.auth.application.service.UserService;
import club.p6e.germ.cloud.auth.controller.support.BaseController;
import club.p6e.germ.cloud.auth.controller.support.model.UserModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    /**
     * 注入服务
     */
    @Resource
    private UserService userService;

    @Resource
    private P6eCloudAuthProperties properties;

    @RequestMapping("/info")
    public ApiResultModel def(UserModel.ParamVo param) {
        // 验证是否开启服务
        if (!properties.getMe().isOpen()) {
            return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
        }
        // 读取用户的 AccessToken 的数据
        final String token = getAccessToken(param.getAccessToken());
        final UserModel.ResultDto result = userService.info(new UserModel.ParamDto().setAccessToken(token));
        return ApiResultModel.build(result);
    }

}
