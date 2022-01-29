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
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends BaseController {

    @Resource
    private DictionaryService dictionaryService;

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
                param.setLanguage(DEFAULT_LANGUAGE);
            }
            final Map<String, List<DictionaryContext.MapResultDto>> resultDto =
                    dictionaryService.list(P6eCopyUtil.run(param, DictionaryContext.ParamDto.class));
            final Map<String, List<DictionaryContext.MapResultVo>> result = new HashMap<>(resultDto.size());
            for (final String key : resultDto.keySet()) {
                result.put(key, P6eCopyUtil.runList(resultDto.get(key), DictionaryContext.MapResultVo.class));
            }
            return ApiResultContext.build(result);
        }
    }

    @GetMapping(value = "/")
    public ApiResultContext getListDefault(DictionaryContext.ParamVo param) {
        return postListData(param);
    }

    @PostMapping(value = "/")
    public ApiResultContext postListDefault(@RequestBody DictionaryContext.ParamVo param) {
        return postListData(param);
    }

    @GetMapping(value = "/list")
    public ApiResultContext getListData(DictionaryContext.ParamVo param) {
        return postListData(param);
    }

    @PostMapping(value = "/list")
    public ApiResultContext postListData(@RequestBody DictionaryContext.ParamVo param) {
        return ApiResultContext.build();
    }

}
