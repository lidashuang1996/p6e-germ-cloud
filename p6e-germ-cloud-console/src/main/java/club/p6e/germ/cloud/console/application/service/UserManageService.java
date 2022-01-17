package club.p6e.germ.cloud.console.application.service;

import club.p6e.germ.cloud.console.controller.support.model.UserContext;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface UserManageService {

    public UserContext.ResultDto update(UserContext.ParamDto param);
    public UserContext.ResultDto delete(UserContext.ParamDto param);

    public UserContext.ListResultDto list(UserContext.ParamDto param);

}
