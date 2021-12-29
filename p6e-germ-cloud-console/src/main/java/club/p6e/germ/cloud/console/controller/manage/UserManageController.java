package club.p6e.germ.cloud.console.controller.manage;

import club.p6e.germ.cloud.console.application.service.UserManageService;
import club.p6e.germ.cloud.console.controller.support.ApiResultModel;
import club.p6e.germ.cloud.console.controller.support.model.UserModel;
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
    private UserManageService userManageService;

    @GetMapping("/list")
    public ApiResultModel getList(UserModel.ParamVo param) {
        return postList(param);
    }

    @PostMapping("/list")
    public ApiResultModel postList(@RequestBody UserModel.ParamVo param) {
        final UserModel.ListResultDto result =
                userManageService.list(P6eCopyUtil.run(param, UserModel.ParamDto.class));
        return ApiResultModel.build(P6eCopyUtil.run(result, UserModel.ListResultVo.class));
    }

    @PutMapping("/{uid}")
    public ApiResultModel putUser(AuthInfo info, @PathVariable Integer uid, @RequestBody UserModel.ParamVo param) {
        if (param == null || param.getStatus() == null) {
            throw new P6eParameterException(this.getClass()
                    + " param <" + UserModel.ParamDto.class + "> is null.");
        } else {
            final UserModel.ResultDto result = userManageService.update(
                    new UserModel.ParamDto()
                            .setId(uid)
                            .setStatus(param.getStatus())
                            .setOperate(info.getOperate())
            );
            return ApiResultModel.build(P6eCopyUtil.run(result, UserModel.ResultVo.class));
        }
    }

    @DeleteMapping("/{uid}")
    public ApiResultModel deleteUser(AuthInfo info, @PathVariable Integer uid) {
        final UserModel.ResultDto result = userManageService.delete(
                new UserModel.ParamDto().setId(uid).setOperate(info.getOperate()));
        return ApiResultModel.build(P6eCopyUtil.run(result, UserModel.ResultVo.class));
    }

}
