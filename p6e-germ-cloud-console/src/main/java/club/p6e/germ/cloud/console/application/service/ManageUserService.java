package club.p6e.germ.cloud.console.application.service;

import club.p6e.germ.cloud.console.controller.support.model.UserContext;

/**
 * 管理/用户 服务
 *
 * @author lidashuang
 * @version 1.0
 */
public interface ManageUserService {

    /**
     * 查询用户列表
     * @param param 请求对象
     * @return 结果对象
     */
    public UserContext.ListResultDto list(UserContext.ParamDto param);

    /**
     * 更新用户
     * @param param 请求对象
     * @return 结果对象
     */
    public UserContext.ResultDto update(UserContext.ParamDto param);

    /**
     * 删除用户
     * @param param 请求对象
     * @return 结果对象
     */
    public UserContext.ResultDto delete(UserContext.ParamDto param);
}
