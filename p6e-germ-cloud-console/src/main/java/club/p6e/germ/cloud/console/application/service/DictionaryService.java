package club.p6e.germ.cloud.console.application.service;

import club.p6e.germ.cloud.console.controller.support.model.DictionaryContext;

import java.util.List;
import java.util.Map;

/**
 * 字典服务
 * @author lidashuang
 * @version 1.0
 */
public interface DictionaryService {

    public Map<String, List<DictionaryContext.MapResultDto>> type(DictionaryContext.ParamDto param);

    public DictionaryContext.ListResultDto list(DictionaryContext.ParamDto param);

    public DictionaryContext.ResultDto create(DictionaryContext.ParamDto param);
    public DictionaryContext.ResultDto delete(DictionaryContext.ParamDto param);
    public DictionaryContext.ResultDto update(DictionaryContext.ParamDto param);

}
