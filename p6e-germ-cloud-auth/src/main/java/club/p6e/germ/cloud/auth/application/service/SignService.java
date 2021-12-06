package club.p6e.germ.cloud.auth.application.service;

import club.p6e.germ.cloud.auth.controller.support.model.SignModel;

/**
 * 登录注册服务
 * @author lidashuang
 * @version 1.0
 */
public interface SignService {

    /**
     * 验证
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.Check.ResultDto check(SignModel.Check.ParamDto param);

    /**
     * 忘记密码
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.ForgetPassword.ResultDto forgetPassword(SignModel.ForgetPassword.ParamDto param);

    /**
     * 删除令牌
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.Out.ResultDto out(SignModel.Out.ParamDto param);

    /**
     * 注销令牌（删除当前用户所有令牌）
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.Clean.ResultDto clean(SignModel.Clean.ParamDto param);

    /**
     * 刷新令牌
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.Refresh.ResultDto refresh(SignModel.Refresh.ParamDto param);

}
