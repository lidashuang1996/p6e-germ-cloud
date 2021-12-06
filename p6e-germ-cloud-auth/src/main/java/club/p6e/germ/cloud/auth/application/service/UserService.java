package club.p6e.germ.cloud.auth.application.service;

import club.p6e.germ.cloud.auth.controller.support.model.UserModel;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface UserService {

    /**
     * 获取用户的信息
     * @param param 参数对象
     * @return 结果对象
     */
    public UserModel.ResultDto info(UserModel.ParamDto param);

}
