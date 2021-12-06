package club.p6e.germ.jurisdiction;

import club.p6e.germ.jurisdiction.cache.ICacheUrl;
import club.p6e.germ.jurisdiction.cache.ICacheUser;
import club.p6e.germ.jurisdiction.model.JurisdictionDb;
import club.p6e.germ.jurisdiction.mybatis.mapper.JurisdictionMapper;
import com.p6e.germ.common.utils.P6eJsonUtil;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class ActuatorDefault implements Actuator {

    @Resource
    private ICacheUrl urlCache;

    @Resource
    private ICacheUser userCache;

    @Resource
    private JurisdictionMapper jurisdictionMapper;

    @Override
    public boolean execute(String method, String url, Integer uid, boolean defStatus, Map<String, String> param) {
        try {
            // 判断请求的 URL 是否存在
            final String uidString = String.valueOf(uid);
            final String urlData = "[" + method.toUpperCase() + ']' + url;
            final String urlContent = urlCache.get(urlData);
            if (urlContent == null) {
                return defStatus;
            } else {
                // 查询 UID 对应的数据
                String userUrlParam = null;
                if (userCache.hasKey(uidString)) {
                    userUrlParam = userCache.get(uidString, urlData);
                } else {
                    final List<JurisdictionDb> jurisdictionList = jurisdictionMapper.selectById(new JurisdictionDb().setUserId(uid));
                    if (jurisdictionList != null) {
                        final Map<String, String> jurisdictionMap = jurisdictionListToMap(jurisdictionList);
                        userUrlParam = jurisdictionMap.get(urlData);
                        userCache.set(uidString, jurisdictionMap);
                    }
                }
                if (userUrlParam == null) {
                    return false;
                } else {
                    // 回写参数数据
                    if (param != null) {
                        final Map<String, String> userUrlParamMap = P6eJsonUtil.fromJsonToMap(userUrlParam, String.class, String.class);
                        if (userUrlParamMap.size() > 0) {
                            param.putAll(userUrlParamMap);
                        }
                    }
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 权限列表转换为键值对
     * @param jurisdictionList 权限列表
     * @return 键值对结果
     */
    private Map<String, String> jurisdictionListToMap(List<JurisdictionDb> jurisdictionList) {
        final Map<String, String> result = new HashMap<>(jurisdictionList.size());
        final Map<String, JurisdictionDb> weight = new HashMap<>(jurisdictionList.size());
        for (JurisdictionDb db : jurisdictionList) {
            final String key = "[" + db.getUrlMethod() + "]" + db.getUrlBaseUrl() + db.getUrlUrl();
            if (weight.get(key) == null) {
                weight.put(key, db);
                result.put(key, db.getRoleParam());
            } else if (weight.get(key).getRoleWeight() < db.getRoleWeight()) {
                weight.put(key, db);
                result.put(key, db.getRoleParam());
            }
        }
        return result;
    }
}
