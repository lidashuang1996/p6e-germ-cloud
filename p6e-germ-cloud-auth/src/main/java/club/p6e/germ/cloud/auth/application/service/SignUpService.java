package club.p6e.germ.cloud.auth.application.service;

import club.p6e.germ.cloud.auth.controller.support.model.SignModel;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface SignUpService {

    /**
     * 账号密码注册
     * @param param 参数对象
     * @return 结果对象
     */
    public SignModel.Up.ResultDto up(SignModel.Up.ParamDto param);

}
