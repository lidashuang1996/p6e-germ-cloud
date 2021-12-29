package club.p6e.germ.cloud.console.application.service;

import club.p6e.germ.cloud.console.controller.support.model.UserModel;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface UserManageService {

    public UserModel.ResultDto update(UserModel.ParamDto param);
    public UserModel.ResultDto delete(UserModel.ParamDto param);

    public UserModel.ListResultDto list(UserModel.ParamDto param);

}
