package club.p6e.germ.cloud.auth.controller;

import club.p6e.germ.cloud.auth.P6eCloudAuthProperties;
import club.p6e.germ.cloud.auth.application.service.SignInService;
import club.p6e.germ.cloud.auth.application.service.SignService;
import club.p6e.germ.cloud.auth.controller.support.ApiResultModel;
import club.p6e.germ.cloud.auth.controller.support.model.SignModel;
import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import club.p6e.germ.cloud.auth.application.service.SignUpService;
import club.p6e.germ.cloud.auth.controller.support.BaseController;
import com.p6e.germ.common.utils.P6eCopyUtil;
import com.p6e.germ.common.utils.P6eFormatUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/sign")
public class SignController extends BaseController {

    /** 类型 账号密码登录 */
    private static final String AP = "AP";
    /** 类型 账号验证码登录 */
    private static final String AC = "AC";
    /** 类型 二维码登录 */
    private static final String QC = "QC";
    /** 类型 注册 */
    private static final String RG = "RG";
    /** 类型 找回密码 */
    private static final String FP = "FP";

    /** 注入登录服务 */
    @Resource
    private SignService signService;

    /** 注入登录服务 */
    @Resource
    private SignInService signInService;

    /** 注入注册服务 */
    @Resource
    private SignUpService signUpService;

    /** 配置文件信息 */
    @Resource
    private P6eCloudAuthProperties properties;

    @PostMapping("/check")
    public ApiResultModel check(@RequestBody SignModel.Check.ParamVo param) {
        if (param == null
                || param.getType() == null
                || param.getVoucher() == null) {
            return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            // 读取类型
            final String type = param.getType();
            final boolean bool = (!(AP.equalsIgnoreCase(type)
                    || AC.equalsIgnoreCase(type)
                    || RG.equalsIgnoreCase(type)
                    || FP.equalsIgnoreCase(type)
                    || QC.equalsIgnoreCase(type)))
                    || ((AC.equalsIgnoreCase(type)
                    || RG.equalsIgnoreCase(type)
                    || FP.equals(type)) && param.getAccount() == null);
            if (bool) {
                return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
            } else {
                if (RG.equalsIgnoreCase(type)) {
                    // 验证是否开启服务
                    if (!properties.getRegister().isOpen()) {
                        return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
                    }
                }
                if (FP.equalsIgnoreCase(type)) {
                    // 验证是否开启服务
                    if (!properties.getForgetPassword().isOpen()) {
                        return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
                    }
                }
                if (AP.equalsIgnoreCase(type) || AC.equalsIgnoreCase(type) || QC.equalsIgnoreCase(type)) {
                    // 验证是否开启服务
                    if (!properties.getLogin().isOpen()) {
                        return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
                    }
                }
                if (AC.equalsIgnoreCase(type) || RG.equalsIgnoreCase(type) || FP.equalsIgnoreCase(type)) {
                    final String account = param.getAccount();
                    final boolean isEmail = P6eFormatUtil.emailVerification(account);
                    final boolean isPhone = P6eFormatUtil.phoneVerification(account);
                    if (!(isEmail || isPhone)) {
                        return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
                    }
                }
                final SignModel.Check.ResultDto result =
                        signService.check(P6eCopyUtil.run(param, SignModel.Check.ParamDto.class));
                if (result.getError() != null) {
                    return ApiResultModel.build(result.getError());
                } else {
                    return ApiResultModel.build(result, SignModel.Check.ResultVo.class);
                }
            }
        }
    }

    @PostMapping("/in")
    public ApiResultModel in(@RequestBody SignModel.In.ParamVo param) {
        if (param == null
                || param.getVoucher() == null
                || param.getAccount() == null
                || param.getPassword() == null) {
            return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            // 验证是否开启服务
            if (!properties.getLogin().isOpen()) {
                return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
            }
            // 验证参数
            final String account = param.getAccount();
            final boolean isEmail = P6eFormatUtil.emailVerification(account);
            final boolean isPhone = P6eFormatUtil.phoneVerification(account);
            if (isEmail || isPhone) {
                final SignModel.In.ResultDto result =
                        signInService.in(P6eCopyUtil.run(param, SignModel.In.ParamDto.class));
                if (result.getError() != null) {
                    return ApiResultModel.build(result.getError());
                } else {
                    return ApiResultModel.build(result, SignModel.In.ResultVo.class);
                }
            } else {
                return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
            }
        }
    }

    @PostMapping("/up")
    public ApiResultModel up(@RequestBody SignModel.Up.ParamVo param) {
        if (param == null
                || param.getVoucher() == null
                || param.getPassword() == null) {
            return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            // 验证是否开启服务
            if (!properties.getRegister().isOpen()) {
                return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
            }
            final SignModel.Up.ResultDto result =
                    signUpService.up(P6eCopyUtil.run(param, SignModel.Up.ParamDto.class));
            if (result.getError() != null) {
                return ApiResultModel.build(result.getError());
            } else {
                return ApiResultModel.build(result, SignModel.Up.ResultVo.class);
            }
        }
    }

    @PostMapping("/code")
    public ApiResultModel code(@RequestBody SignModel.Code.ParamVo param) {
        if (param == null
                || param.getCode() == null
                || param.getVoucher() == null
                || param.getAccount() == null) {
            return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            // 验证是否开启服务
            if (!properties.getLogin().isOpen()) {
                return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
            }
            final SignModel.Code.ResultDto result =
                    signInService.code(P6eCopyUtil.run(param, SignModel.Code.ParamDto.class));
            if (result.getError() != null) {
                return ApiResultModel.build(result.getError());
            } else {
                return ApiResultModel.build(result, SignModel.Code.ResultVo.class);
            }
        }
    }

    @PostMapping("/qrcode")
    public ApiResultModel qrcode(@RequestBody SignModel.QrCode.ParamVo param) {
        if (param == null || param.getVoucher() == null) {
            return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            // 验证是否开启服务
            if (!properties.getLogin().isOpen()) {
                return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
            }
            final SignModel.QrCode.ResultDto result =
                    signInService.qrCode(P6eCopyUtil.run(param, SignModel.QrCode.ParamDto.class));
            if (result.getError() != null) {
                return ApiResultModel.build(result.getError());
            } else {
                return ApiResultModel.build(result, SignModel.QrCode.ResultVo.class);
            }
        }
    }

    @PostMapping("/forget_password")
    public ApiResultModel forgetPassword(@RequestBody SignModel.ForgetPassword.ParamVo param) {
        if (param == null || param.getVoucher() == null) {
            return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            // 验证是否开启服务
            if (!properties.getRegister().isOpen()) {
                return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
            }
            final SignModel.ForgetPassword.ResultDto result =
                    signService.forgetPassword(P6eCopyUtil.run(param, SignModel.ForgetPassword.ParamDto.class));
            if (result.getError() != null) {
                return ApiResultModel.build(result.getError());
            } else {
                return ApiResultModel.build(result, SignModel.ForgetPassword.ResultVo.class);
            }
        }
    }

    @GetMapping("/verification")
    public ApiResultModel verification(SignModel.Verification.ParamVo param) {
        if (param == null || param.getVoucher() == null) {
            return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            // 验证是否开启服务
            if (!properties.getLogin().isOpen()) {
                return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
            }
            final String accessToken = getAccessToken(param.getAccessToken());
            if (accessToken == null) {
                return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
            } else {
                final SignModel.Verification.ResultDto result =
                        signInService.verification(P6eCopyUtil.run(param, SignModel.Verification.ParamDto.class));
                if (result.getError() != null) {
                    return ApiResultModel.build(result.getError());
                } else {
                    return ApiResultModel.build(result, SignModel.Verification.ResultVo.class);
                }
            }
        }
    }

    @GetMapping("/qq")
    public ApiResultModel qq(SignModel.Qq.ParamVo param) {
        if (param == null || param.getVoucher() == null) {
            return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            // 验证是否开启服务
            if (!properties.getLogin().isOpen()) {
                return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
            }
            final SignModel.Qq.ResultDto result =
                    signInService.qq(P6eCopyUtil.run(param, SignModel.Qq.ParamDto.class));
            return ApiResultModel.build(result);
        }
    }

    @GetMapping("/qq/callback")
    public ApiResultModel qqCallback(SignModel.QqCallback.ParamVo param) {
        if (param == null || param.getCode() == null || param.getState() == null) {
            return ApiResultModel.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            // 验证是否开启服务
            if (!properties.getLogin().isOpen()) {
                return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
            }
            final SignModel.QqCallback.ResultDto result =
                    signInService.qqCallback(P6eCopyUtil.run(param, SignModel.QqCallback.ParamDto.class));
            return ApiResultModel.build(result);
        }
    }

    @GetMapping("/wechat")
    public ApiResultModel wechat() {
        // 验证是否开启服务
        if (!properties.getLogin().isOpen()) {
            return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
        }
        return ApiResultModel.build(ErrorModel.SERVICE_EXCEPTION);
    }

    @GetMapping("/wechat/callback")
    public ApiResultModel wechatCallback() {
        // 验证是否开启服务
        if (!properties.getLogin().isOpen()) {
            return ApiResultModel.build(ErrorModel.SERVICE_NOT_ENABLE);
        }
        return ApiResultModel.build(ErrorModel.SERVICE_EXCEPTION);
    }

}
