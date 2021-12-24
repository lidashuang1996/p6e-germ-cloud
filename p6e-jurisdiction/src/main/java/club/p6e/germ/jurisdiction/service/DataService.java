package club.p6e.germ.jurisdiction.service;

import club.p6e.germ.jurisdiction.model.JurisdictionConditionModel;
import club.p6e.germ.jurisdiction.model.JurisdictionModel;
import club.p6e.germ.jurisdiction.model.JurisdictionUrlModel;
import java.util.Map;

/**
 * 数据服务
 * DB / REDIS / 内存 <---- 服务 ----> 数据
 * @author lidashuang
 * @version 1.0
 */
public interface DataService {

    /**
     * 根据路径获取对应的 URL 权限对象
     * @param path 路径
     * @return URL 权限对象
     */
    public JurisdictionConditionModel getPathJurisdiction(String path, String method);

    /**
     * 根据用户的 UID 权限对象
     * @param uid 用户 ID
     * @return 用户的 UID 权限对象
     */
    public Map<Integer, JurisdictionModel> getUserJurisdictionList(Integer uid);

}
