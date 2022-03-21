package club.p6e.germ.cloud.console.controller.manage;

import club.p6e.germ.cloud.console.application.service.ManageJurisdictionService;
import club.p6e.germ.cloud.console.controller.support.ApiResultContext;
import club.p6e.germ.cloud.console.controller.support.BaseController;
import club.p6e.germ.cloud.console.controller.support.model.JurisdictionContext;
import club.p6e.germ.cloud.console.infrastructure.model.ErrorModel;
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
    private ManageJurisdictionService jurisdictionManageService;

    /*--------------[ url (START)]--------------*/

    @GetMapping("/url")
    public ApiResultContext getUrl(JurisdictionContext.Url.ParamVo param) {
        return postUrlList(param);
    }

    @PostMapping("/url")
    public ApiResultContext postUrl(@RequestBody JurisdictionContext.Url.ParamVo param) {
        return postUrlList(param);
    }

    @GetMapping("/url/list")
    public ApiResultContext getUrlList(JurisdictionContext.Url.ParamVo param) {
        return postUrlList(param);
    }

    @PostMapping("/url/list")
    public ApiResultContext postUrlList(@RequestBody JurisdictionContext.Url.ParamVo param) {
        final JurisdictionContext.Url.ListResultDto result =
                jurisdictionManageService.urlList(P6eCopyUtil.run(param, JurisdictionContext.Url.ParamDto.class));
        return ApiResultContext.build(P6eCopyUtil.run(result, JurisdictionContext.Url.ListResultVo.class));
    }

    @RequestMapping(value = "/url/create", method = { RequestMethod.POST })
    public ApiResultContext createUrl(@RequestBody JurisdictionContext.Url.ParamVo param) {
        if (param == null
                || param.getName() == null
                || param.getUrl() == null
                || param.getConfig() == null
                || param.getBaseUrl() == null
                || param.getMethod() == null) {
            return ApiResultContext.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            final JurisdictionContext.Url.ResultDto resultDto = jurisdictionManageService.createUrl(
                    P6eCopyUtil.run(param, JurisdictionContext.Url.ParamDto.class).setOperate(DEFAULT_OPERATE));
            return ApiResultContext.build(P6eCopyUtil.run(resultDto, JurisdictionContext.Url.ResultVo.class));
        }
    }

    @RequestMapping(value ="/url/update/{id}", method = { RequestMethod.POST, RequestMethod.PUT })
    public ApiResultContext updateUrl(@PathVariable Integer id, @RequestBody JurisdictionContext.Url.ParamVo param) {
        if (param == null
                || param.getName() == null
                || param.getUrl() == null
                || param.getConfig() == null
                || param.getBaseUrl() == null
                || param.getMethod() == null) {
            return ApiResultContext.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            final JurisdictionContext.Url.ResultDto resultDto = jurisdictionManageService.updateUrl(
                    P6eCopyUtil.run(param, JurisdictionContext.Url.ParamDto.class).setOperate(DEFAULT_OPERATE));
            return ApiResultContext.build(P6eCopyUtil.run(resultDto, JurisdictionContext.Url.ResultVo.class));
        }
    }

    @RequestMapping(value ="/url/delete/{id}", method = { RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
    public ApiResultContext deleteUrl(@PathVariable Integer id) {
        final JurisdictionContext.Url.ResultDto resultDto = jurisdictionManageService.deleteUrl(
                new JurisdictionContext.Url.ParamDto().setId(id).setOperate(DEFAULT_OPERATE));
        return ApiResultContext.build(P6eCopyUtil.run(resultDto, JurisdictionContext.Url.ResultVo.class));
    }

    /*--------------[ url (END)]--------------*/





    /*--------------[ url group (START)]--------------*/

    @GetMapping("/url/group")
    public ApiResultContext getUrlGroup(JurisdictionContext.UrlGroup.ParamVo param) {
        return postGroupList(param);
    }

    @PostMapping("/url/group")
    public ApiResultContext postUrlGroup(@RequestBody JurisdictionContext.UrlGroup.ParamVo param) {
        return postGroupList(param);
    }

    @GetMapping("/url/group/list")
    public ApiResultContext getGroupList(JurisdictionContext.UrlGroup.ParamVo param) {
        return postGroupList(param);
    }

    @PostMapping("/url/group/list")
    public ApiResultContext postGroupList(@RequestBody JurisdictionContext.UrlGroup.ParamVo param) {
        final JurisdictionContext.UrlGroup.ListResultDto result =
                jurisdictionManageService.urlGroupList(P6eCopyUtil.run(param, JurisdictionContext.UrlGroup.ParamDto.class));
        return ApiResultContext.build(P6eCopyUtil.run(result, JurisdictionContext.UrlGroup.ListResultVo.class));
    }

    /*--------------[ url group (END)]--------------*/
}
