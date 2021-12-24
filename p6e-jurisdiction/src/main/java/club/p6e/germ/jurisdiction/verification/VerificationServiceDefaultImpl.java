package club.p6e.germ.jurisdiction.verification;

import club.p6e.germ.jurisdiction.model.JurisdictionUrlModel;
import club.p6e.germ.jurisdiction.service.JurisdictionService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Component
public class VerificationServiceDefaultImpl implements VerificationService {

    @Resource
    private JurisdictionService service;

    @Override
    public boolean execute(Integer uid, String path) {
        final JurisdictionUrlModel pathJurisdiction = service.getPathJurisdiction(path);
        final Map<Integer, JurisdictionUrlModel> userJurisdictionList = service.getUserJurisdictionList(uid);
        final JurisdictionUrlModel userPathJurisdiction = userJurisdictionList.get(pathJurisdiction.getId());
        if (userPathJurisdiction == null) {
            return false;
        } else {
            return true;
//            userPathJurisdiction.get
        }
    }

}
