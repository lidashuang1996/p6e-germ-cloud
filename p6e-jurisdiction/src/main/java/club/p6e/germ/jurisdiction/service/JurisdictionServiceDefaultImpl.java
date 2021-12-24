package club.p6e.germ.jurisdiction.service;

import club.p6e.germ.jurisdiction.model.JurisdictionUrlModel;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class JurisdictionServiceDefaultImpl implements JurisdictionService {

    @Override
    public Map<Integer, JurisdictionUrlModel> getUserJurisdictionList(Integer uid) {
        return null;
    }

    @Override
    public JurisdictionUrlModel getPathJurisdiction(String path) {
        return null;
    }
}
