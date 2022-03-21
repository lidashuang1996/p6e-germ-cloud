package club.p6e.germ.cloud.console.controller.manage;

import club.p6e.germ.cloud.console.application.service.ManageUserService;
import club.p6e.germ.cloud.console.controller.support.ApiResultContext;
import club.p6e.germ.cloud.console.controller.support.model.UserContext;
import club.p6e.germ.cloud.console.infrastructure.auth.AuthInfo;
import club.p6e.germ.cloud.console.infrastructure.error.P6eParameterException;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/manage/user")
public class UserManageController {

    @Resource
    private ManageUserService userManageService;

    @GetMapping("/list")
    public ApiResultContext getList(UserContext.ParamVo param) {
        return postList(param);
    }

    @PostMapping("/list")
    public ApiResultContext postList(@RequestBody UserContext.ParamVo param) {
        final UserContext.ListResultDto result =
                userManageService.list(P6eCopyUtil.run(param, UserContext.ParamDto.class));
        return ApiResultContext.build(P6eCopyUtil.run(result, UserContext.ListResultVo.class));
    }

    @PutMapping("/{uid}")
    public ApiResultContext putUser(AuthInfo info, @PathVariable Integer uid, @RequestBody UserContext.ParamVo param) {
        if (param == null || param.getStatus() == null) {
            throw new P6eParameterException(this.getClass()
                    + " param <" + UserContext.ParamDto.class + "> is null.");
        } else {
            final UserContext.ResultDto result = userManageService.update(
                    new UserContext.ParamDto()
                            .setId(uid)
                            .setStatus(param.getStatus())
                            .setOperate(info.getOperate())
            );
            return ApiResultContext.build(P6eCopyUtil.run(result, UserContext.ResultVo.class));
        }
    }

    @DeleteMapping("/{uid}")
    public ApiResultContext deleteUser(AuthInfo info, @PathVariable Integer uid) {
        final UserContext.ResultDto result = userManageService.delete(
                new UserContext.ParamDto().setId(uid).setOperate(info.getOperate()));
        return ApiResultContext.build(P6eCopyUtil.run(result, UserContext.ResultVo.class));
    }

}
