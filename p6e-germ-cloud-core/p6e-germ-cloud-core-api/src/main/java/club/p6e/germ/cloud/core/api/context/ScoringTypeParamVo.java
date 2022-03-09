package club.p6e.germ.cloud.core.api.context;

import lombok.Data;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
public class ScoringTypeParamVo {
    /** 名称 */
    private String name;
    /** 执行器名称 */
    private String actuator;
    /** 积分 */
    private Integer fraction;
    /** 是否自己定义状态 0-不开启 1-开启 */
    private Integer customStatus;
    /** 是否自己定义 */
    private ScoringCustomParamVo customContent;
    private Integer keyValueStatus;
    /** 缓存分数 */
    private ScoringFractionKeyValueParamVo keyValueContent;
}
