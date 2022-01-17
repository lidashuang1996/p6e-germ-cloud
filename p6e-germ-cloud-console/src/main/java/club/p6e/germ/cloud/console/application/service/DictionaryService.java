package club.p6e.germ.cloud.console.application.service;

import club.p6e.germ.cloud.console.controller.support.model.DictionaryContext;

import java.util.List;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface DictionaryService {

    public Map<String, List<DictionaryContext.MapResultDto>> list(DictionaryContext.ParamDto param);

}
