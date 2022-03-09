package club.p6e.germ.cloud.core.api.context;

import lombok.Data;

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
public class ScoringFractionKeyValueParamVo {
    /** 建 */
    private String key;
    /** 值 */
    private Map<String, Double> value;
}
