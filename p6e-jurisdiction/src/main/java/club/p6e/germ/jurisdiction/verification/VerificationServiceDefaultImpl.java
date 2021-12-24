package club.p6e.germ.jurisdiction.verification;

import club.p6e.germ.jurisdiction.model.JurisdictionConditionModel;
import club.p6e.germ.jurisdiction.model.JurisdictionModel;
import club.p6e.germ.jurisdiction.service.DataService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class VerificationServiceDefaultImpl implements VerificationService {

    /** 判断类型 */
    private static final String JUDGE_TYPE = "JUDGE_TYPE";
    /** 多种类型 */
    private static final String MULTI_TYPE = "MULTI_TYPE";

    /** FALSE 的 VALUE */
    private static final String JUDGE_FALSE_VALUE = "FALSE";

    /** 相同规则 */
    private static final String EQ_RULE = "EQ_RULE";
    /** 小于规则 */
    private static final String LT_RULE = "LT_RULE";
    /** 大于规则 */
    private static final String GT_RULE = "GT_RULE";

    @Resource
    private DataService service;

    @Override
    public boolean execute(Integer uid, String path, String method) {
        final JurisdictionConditionModel pathJurisdictionCondition = service.getPathJurisdiction(path, method);
        if (pathJurisdictionCondition == null) {
            return true;
        } else {
            final Map<Integer, JurisdictionModel> userJurisdictionMap = service.getUserJurisdictionList(uid);
            final JurisdictionModel jurisdictionModel = userJurisdictionMap.get(pathJurisdictionCondition.getUid());
            if (jurisdictionModel == null) {
                return false;
            } else {
                final String type = pathJurisdictionCondition.getType();
                if (type.equalsIgnoreCase(JUDGE_TYPE)) {
                    return !JUDGE_FALSE_VALUE.equalsIgnoreCase(jurisdictionModel.getParam());
                } else if (type.equalsIgnoreCase(MULTI_TYPE)) {
                    try {
                        final String param = jurisdictionModel.getParam();
                        final String rule = pathJurisdictionCondition.getRule();
                        final String value = pathJurisdictionCondition.getValue();
                        if (value == null || param == null) {
                            return false;
                        } else if (rule.equalsIgnoreCase(EQ_RULE)) {
                            // 相同则有权限
                            return value.equalsIgnoreCase(param);
                        } else if (rule.equalsIgnoreCase(LT_RULE)) {
                            // 小于则有权限
                            return Double.parseDouble(param) < Double.parseDouble(value);
                        } else if (rule.equalsIgnoreCase(GT_RULE)) {
                            // 大于则有权限
                            return Double.parseDouble(param) > Double.parseDouble(value);
                        } else {
                            return false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
    }
}
