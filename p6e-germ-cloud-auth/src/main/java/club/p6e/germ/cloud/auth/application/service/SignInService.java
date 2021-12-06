package club.p6e.germ.cloud.auth.application.service;

import club.p6e.germ.cloud.auth.controller.support.model.SignModel;

/**
 * 登录服务
 * @author lidashuang
 * @version 1.0
 */
public interface SignInService {

    /**
     * 账号密码登录
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.In.ResultDto in(SignModel.In.ParamDto param);

    /**
     * 验证码登录
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.Code.ResultDto code(SignModel.Code.ParamDto param);

    /**
     * 二维码登录
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.QrCode.ResultDto qrCode(SignModel.QrCode.ParamDto param);

    /**
     * 验证令牌（快捷登录）
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.Verification.ResultDto verification(SignModel.Verification.ParamDto param);

    /**
     * QQ 第三方登录
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.Qq.ResultDto qq(SignModel.Qq.ParamDto param);

    /**
     * QQ 回调
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.QqCallback.ResultDto qqCallback(SignModel.QqCallback.ParamDto param);

    /**
     * 微信第三方登录
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.Wechat.ParamDto wechat(SignModel.Wechat.ParamDto param);

    /**
     * 微信回调
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.WechatCallback.ParamDto wechatCallback(SignModel.WechatCallback.ParamDto param);

}
