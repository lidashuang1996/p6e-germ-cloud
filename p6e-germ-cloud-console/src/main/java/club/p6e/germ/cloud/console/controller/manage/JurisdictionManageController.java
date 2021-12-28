package club.p6e.germ.cloud.console.controller.manage;

import club.p6e.germ.cloud.console.application.service.JurisdictionManageService;
import club.p6e.germ.cloud.console.controller.support.ApiResultModel;
import club.p6e.germ.cloud.console.controller.support.BaseController;
import club.p6e.germ.cloud.console.controller.support.model.JurisdictionModel;
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
    public ApiResultModel getList(JurisdictionModel.Path.ParamVo param) {
        return postList(param);
    }

    @PostMapping("/path/list")
    public ApiResultModel postList(@RequestBody JurisdictionModel.Path.ParamVo param) {
        final JurisdictionModel.Path.ListResultDto result =
                jurisdictionManageService.pathList(P6eCopyUtil.run(param, JurisdictionModel.Path.ParamDto.class));
        System.out.println(result);
        return ApiResultModel.build(P6eCopyUtil.run(result, JurisdictionModel.Path.ListResultVo.class));
    }

    @GetMapping("/path/group/list")
    public ApiResultModel getGroupList(JurisdictionModel.PathGroup.ParamVo param) {
        return postGroupList(param);
    }

    @PostMapping("/path/group/list")
    public ApiResultModel postGroupList(@RequestBody JurisdictionModel.PathGroup.ParamVo param) {
        final JurisdictionModel.PathGroup.ListResultDto result =
                jurisdictionManageService.pathGroupList(P6eCopyUtil.run(param, JurisdictionModel.PathGroup.ParamDto.class));
        return ApiResultModel.build(P6eCopyUtil.run(result, JurisdictionModel.PathGroup.ListResultVo.class));
    }

}
