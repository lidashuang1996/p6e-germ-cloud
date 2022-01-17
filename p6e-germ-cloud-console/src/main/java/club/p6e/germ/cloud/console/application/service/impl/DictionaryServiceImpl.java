package club.p6e.germ.cloud.console.application.service.impl;

import club.p6e.germ.cloud.console.application.service.DictionaryService;
import club.p6e.germ.cloud.console.controller.support.model.DictionaryContext;
import club.p6e.germ.cloud.console.domain.aggregate.dictionary.DictionaryAggregate;
import club.p6e.germ.cloud.console.infrastructure.model.DictionaryModel;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Override
    public Map<String, List<DictionaryContext.MapResultDto>> list(DictionaryContext.ParamDto param) {
        final Map<String, List<DictionaryContext.MapResultDto>> result = new HashMap<>();
        final DictionaryAggregate dictionaryAggregate = new DictionaryAggregate(param.getTypes(), param.getLanguage());
        final Map<String, List<DictionaryModel>> map = dictionaryAggregate.getResult();
        for (final String key : map.keySet()) {
            result.put(key, P6eCopyUtil.runList(map.get(key), DictionaryContext.MapResultDto.class));
        }
        return result;
    }

}
