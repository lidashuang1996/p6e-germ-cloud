package club.p6e.germ.jurisdiction.service;

import club.p6e.germ.jurisdiction.model.JurisdictionUrlModel;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface JurisdictionService {

    public Map<Integer, JurisdictionUrlModel> getUserJurisdictionList(Integer uid);

    public JurisdictionUrlModel getPathJurisdiction(String path);

}
