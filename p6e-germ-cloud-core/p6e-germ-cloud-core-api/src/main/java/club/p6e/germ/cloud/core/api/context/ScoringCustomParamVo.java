package club.p6e.germ.cloud.core.api.context;

import lombok.Data;

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
public class ScoringCustomParamVo {
    /** 公式 */
    private String formula;
    /** 公式的映射 */
    private Map<String, Map<String, Double>> formulaMap;
}
