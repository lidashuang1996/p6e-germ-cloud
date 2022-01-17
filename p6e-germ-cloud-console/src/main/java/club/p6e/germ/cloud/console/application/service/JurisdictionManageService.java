package club.p6e.germ.cloud.console.application.service;

import club.p6e.germ.cloud.console.controller.support.model.JurisdictionContext;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface JurisdictionManageService {


    public JurisdictionContext.Path.ListResultDto pathList(JurisdictionContext.Path.ParamDto param);

    public JurisdictionContext.PathGroup.ListResultDto pathGroupList(JurisdictionContext.PathGroup.ParamDto param);

}
