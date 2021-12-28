package club.p6e.germ.cloud.console.application.service;

import club.p6e.germ.cloud.console.controller.support.model.JurisdictionModel;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface JurisdictionManageService {


    public JurisdictionModel.Path.ListResultDto pathList(JurisdictionModel.Path.ParamDto param);

    public JurisdictionModel.PathGroup.ListResultDto pathGroupList(JurisdictionModel.PathGroup.ParamDto param);

}
