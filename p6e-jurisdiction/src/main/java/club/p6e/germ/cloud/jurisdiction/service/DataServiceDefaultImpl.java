package club.p6e.germ.cloud.jurisdiction.service;

import club.p6e.germ.cloud.jurisdiction.model.*;
import club.p6e.germ.cloud.jurisdiction.repository.JurisdictionUrlRepository;
import club.p6e.germ.cloud.jurisdiction.repository.JurisdictionUserRelationUrlGroupRepository;
import com.p6e.germ.common.utils.P6eJsonUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class DataServiceDefaultImpl implements DataService {

    /** 判断类型 */
    private static final String JUDGE_TYPE = "JUDGE_TYPE";
    /** 相同规则 */
    private static final String EQ_RULE = "EQ_RULE";
    /** 默认的 VALUE */
    private static final String DEFAULT_VALUE = "";
    /** 类型 */
    private static final String TYPE_NAME = "type";
    /** 规则 */
    private static final String RULE_NAME = "rule";
    /** 参数 */
    private static final String VALUE_NAME = "value";

    @Resource
    private JurisdictionUrlRepository jurisdictionUrlRepository;

    @Resource
    private JurisdictionUserRelationUrlGroupRepository jurisdictionUserRelationUrlGroupRepository;

    @Override
    public JurisdictionConditionModel getPathJurisdiction(String path, String method) {
        final Optional<JurisdictionUrlModel> optional = jurisdictionUrlRepository.findOne(
                (Specification<JurisdictionUrlModel>) (root, query, criteriaBuilder) -> {
                    query.where(criteriaBuilder.and(
                            criteriaBuilder.equal(root.get("method"), 0),
                            criteriaBuilder.equal(root.get("is_delete"), 0),
                            criteriaBuilder.equal(criteriaBuilder.concat(root.get("base_url"), root.get("url")), path)
                    ));
                    return query.getRestriction();
                });
        if (optional.isPresent()) {
            final int uid = optional.get().getId();
            final String config = optional.get().getConfig();
            try {
                if (config == null) {
                    return new JurisdictionConditionModel(uid, JUDGE_TYPE, EQ_RULE, DEFAULT_VALUE);
                } else {
                    final Map<String, Object> map = P6eJsonUtil.fromJsonToMap(config, String.class, Object.class);
                    final String type = map.get(TYPE_NAME) == null ? JUDGE_TYPE : String.valueOf(map.get(TYPE_NAME));
                    final String rule = map.get(RULE_NAME) == null ? EQ_RULE : String.valueOf(map.get(RULE_NAME));
                    final String value = map.get(VALUE_NAME) == null ? DEFAULT_VALUE : String.valueOf(map.get(VALUE_NAME));
                    return new JurisdictionConditionModel(uid, type, rule, value);
                }
            } catch (Exception e) {
                return new JurisdictionConditionModel(uid, JUDGE_TYPE, EQ_RULE, DEFAULT_VALUE);
            }
        } else {
            return null;
        }
    }

    @Override
    public Map<Integer, JurisdictionModel> getUserJurisdictionList(Integer uid) {
        final Map<Integer, JurisdictionModel> result = new HashMap<>(16);
        final List<JurisdictionUserRelationUrlGroupModel> jurisdictionUserRelationUrlGroupModelList
                = jurisdictionUserRelationUrlGroupRepository.findAllByUid(uid);
        // 遍历权限组
        for (final JurisdictionUserRelationUrlGroupModel item : jurisdictionUserRelationUrlGroupModelList) {
            final JurisdictionUrlGroupModel group = item.getUrlGroup();
            // 遍历权限组里面的权限
            for (final JurisdictionUrlGroupRelationUrlModel url : group.getUrls()) {
                final JurisdictionModel jm = result.get(url.getUid());
                if (jm == null || jm.getWeight() < group.getWeight()) {
                    result.put(url.getUid(), new JurisdictionModel(url.getUid(), group.getWeight(), url.getParam()));
                }
            }
        }
        return result;
    }
}
