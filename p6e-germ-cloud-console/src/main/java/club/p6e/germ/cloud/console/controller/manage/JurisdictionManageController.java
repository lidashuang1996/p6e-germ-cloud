package club.p6e.germ.cloud.console.controller.manage;

import club.p6e.germ.cloud.console.application.service.JurisdictionManageService;
import club.p6e.germ.cloud.console.controller.support.ApiResultContext;
import club.p6e.germ.cloud.console.controller.support.BaseController;
import club.p6e.germ.cloud.console.controller.support.model.JurisdictionContext;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/manage/jurisdiction")
public class JurisdictionManageController extends BaseController {

    @Resource
    private JurisdictionManageService jurisdictionManageService;

    @GetMapping("/path/list")
    public ApiResultContext getList(JurisdictionContext.Path.ParamVo param) {
        return postList(param);
    }

    @PostMapping("/path/list")
    public ApiResultContext postList(@RequestBody JurisdictionContext.Path.ParamVo param) {
        final JurisdictionContext.Path.ListResultDto result =
                jurisdictionManageService.pathList(P6eCopyUtil.run(param, JurisdictionContext.Path.ParamDto.class));
        return ApiResultContext.build(P6eCopyUtil.run(result, JurisdictionContext.Path.ListResultVo.class));
    }

    @GetMapping("/path/group/list")
    public ApiResultContext getGroupList(JurisdictionContext.PathGroup.ParamVo param) {
        return postGroupList(param);
    }

    @PostMapping("/path/group/list")
    public ApiResultContext postGroupList(@RequestBody JurisdictionContext.PathGroup.ParamVo param) {
        final JurisdictionContext.PathGroup.ListResultDto result =
                jurisdictionManageService.pathGroupList(P6eCopyUtil.run(param, JurisdictionContext.PathGroup.ParamDto.class));
        return ApiResultContext.build(P6eCopyUtil.run(result, JurisdictionContext.PathGroup.ListResultVo.class));
    }

}
