package club.p6e.germ.cloud.console.controller;

import club.p6e.germ.cloud.console.application.service.DictionaryService;
import club.p6e.germ.cloud.console.controller.support.ApiResultContext;
import club.p6e.germ.cloud.console.controller.support.BaseController;
import club.p6e.germ.cloud.console.controller.support.model.DictionaryContext;
import club.p6e.germ.cloud.console.infrastructure.model.ErrorModel;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 字典相关的接口
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends BaseController {

    @Resource
    private DictionaryService dictionaryService;

    /*--------------[ def (START)]--------------*/

    @GetMapping(value = "")
    public ApiResultContext getDefault(DictionaryContext.ParamVo param) {
        return postDefault(param);
    }

    @PostMapping(value = "")
    public ApiResultContext postDefault(@RequestBody DictionaryContext.ParamVo param) {
        if (param == null || (param.getType() == null && param.getTypes() == null)) {
            return ApiResultContext.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            // 处理一下参数
            if (param.getType() != null) {
                final List<String> list;
                if (param.getTypes() == null) {
                    list = new ArrayList<>();
                } else {
                    list = Arrays.asList(param.getTypes());
                }
                list.addAll(Arrays.asList(param.getType().split(",")));
                param.setTypes(list.toArray(new String[0]));
            }
            if (param.getLanguage() == null) {
                // 写入默认的语言
                param.setLanguage(DEFAULT_LANGUAGE);
            }
            final Map<String, List<DictionaryContext.MapResultDto>> resultDto =
                    dictionaryService.type(P6eCopyUtil.run(param, DictionaryContext.ParamDto.class));
            final Map<String, List<DictionaryContext.MapResultVo>> result = new HashMap<>(resultDto.size());
            for (final String key : resultDto.keySet()) {
                result.put(key, P6eCopyUtil.runList(resultDto.get(key), DictionaryContext.MapResultVo.class));
            }
            return ApiResultContext.build(result);
        }
    }

    /*--------------[ def (END)]--------------*/





    /*--------------[ list (START)]--------------*/

    @GetMapping(value = "/")
    public ApiResultContext getListDataDefault(DictionaryContext.ParamVo param) {
        return postListData(param);
    }

    @PostMapping(value = "/")
    public ApiResultContext postListDataDefault(@RequestBody DictionaryContext.ParamVo param) {
        return postListData(param);
    }

    @GetMapping(value = "/list")
    public ApiResultContext getListData(DictionaryContext.ParamVo param) {
        return postListData(param);
    }

    @PostMapping(value = "/list")
    public ApiResultContext postListData(@RequestBody DictionaryContext.ParamVo param) {
        if (param.getLanguage() == null) {
            // 写入默认的语言
            param.setLanguage(DEFAULT_LANGUAGE);
        }
        final DictionaryContext.ListResultDto resultDto =
                dictionaryService.list(P6eCopyUtil.run(param, DictionaryContext.ParamDto.class));
        return ApiResultContext.build(P6eCopyUtil.run(resultDto, DictionaryContext.ListResultVo.class));
    }

    /*--------------[ list (END)]--------------*/





    /*--------------[ create (START)]--------------*/

    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    public ApiResultContext create(@RequestBody DictionaryContext.ParamVo param) {
        if (param == null
                || param.getType() == null
                || param.getLanguage() == null
                || param.getKey() == null
                || param.getValue() == null) {
            return ApiResultContext.build(ErrorModel.PARAMETER_EXCEPTION);
        } else {
            final DictionaryContext.ResultDto resultDto = dictionaryService.create(
                    P6eCopyUtil.run(param, DictionaryContext.ParamDto.class).setOperate(DEFAULT_OPERATE));
            return ApiResultContext.build(P6eCopyUtil.run(resultDto, DictionaryContext.ResultVo.class));
        }
    }

    /*--------------[ create (END)]--------------*/




    /*--------------[ update (START)]--------------*/

    @RequestMapping(value = "/update/{id}", method = { RequestMethod.PUT, RequestMethod.POST })
    public ApiResultContext update(@PathVariable Integer id, @RequestBody DictionaryContext.ParamVo param) {
        final DictionaryContext.ResultDto resultDto = dictionaryService.update(
                P6eCopyUtil.run(param, DictionaryContext.ParamDto.class).setId(id).setOperate(DEFAULT_OPERATE));
        return ApiResultContext.build(P6eCopyUtil.run(resultDto, DictionaryContext.ResultVo.class));
    }

    /*--------------[ update (END)]--------------*/





    /*--------------[ delete (START)]--------------*/

    @RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST })
    public ApiResultContext delete(@PathVariable Integer id) {
        final DictionaryContext.ResultDto resultDto = dictionaryService.delete(
                new DictionaryContext.ParamDto().setId(id).setOperate(DEFAULT_OPERATE));
        return ApiResultContext.build(P6eCopyUtil.run(resultDto, DictionaryContext.ResultVo.class));
    }

    /*--------------[ delete (END)]--------------*/
}
