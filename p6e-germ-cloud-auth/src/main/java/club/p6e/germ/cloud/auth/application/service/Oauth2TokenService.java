package club.p6e.germ.cloud.auth.application.service;

import club.p6e.germ.cloud.auth.controller.support.model.Oauth2Model;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface Oauth2TokenService {

    /**
     * 客户端模式
     * @param param 参数对象
     * @return 结果对象
     */
    public Oauth2Model.Client.ResultDto client(Oauth2Model.Client.ParamDto param);

    /**
     * 账号密码模式
     * @param param 参数对象
     * @return 结果对象
     */
    public Oauth2Model.Password.ResultDto password(Oauth2Model.Password.ParamDto param);

    /**
     * 认证 CODE 的回调模式
     * @param param 参数对象
     * @return 结果对象
     */
    public Oauth2Model.AuthCode.ResultDto authCode(Oauth2Model.AuthCode.ParamDto param);

}
