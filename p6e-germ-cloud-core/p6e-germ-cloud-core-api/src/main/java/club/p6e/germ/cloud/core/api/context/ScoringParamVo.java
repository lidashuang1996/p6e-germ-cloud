package club.p6e.germ.cloud.core.api.context;

import lombok.Data;

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
public class ScoringParamVo {
    /** 积分的 ID */
    private String sId;
    /** 积分的时间 */
    private Long sDate= 0L;
    /** 房间的编号 */
    private String rid;
    /** 状态 0-未开始 1-开始 -1 结束 */
    private Integer status = 0;
    /** 礼物类型 */
    private ScoringTypeParamVo giftType;
    /** 分享的类型 */
    private ScoringTypeParamVo shareType;
    /** 弹幕的类型 */
    private ScoringTypeParamVo barrageType;
    /** 扩展的数据 */
    private Map<String, String> spare;
}
