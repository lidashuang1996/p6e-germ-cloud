package club.p6e.germ.cloud.auth.application.service;

import club.p6e.germ.cloud.auth.controller.support.model.Oauth2Model;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface Oauth2AuthService {

    /**
     * 认证 CODE 模式
     * @param param 参数对象
     * @return 结果对象
     */
    public Oauth2Model.Code.ResultDto code(Oauth2Model.Code.ParamDto param);

    /**
     * 认证 CODE 模式，二次确认
     * @param param 参数对象
     * @return 结果对象
     */
    public Oauth2Model.Confirm.ResultDto confirm(Oauth2Model.Confirm.ParamDto param);
}
