package club.p6e.germ.cloud.console.application.service;

import club.p6e.germ.cloud.console.controller.support.model.JurisdictionContext;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface ManageJurisdictionService {


    public JurisdictionContext.Url.ListResultDto urlList(JurisdictionContext.Url.ParamDto param);

    public JurisdictionContext.Url.ResultDto createUrl(JurisdictionContext.Url.ParamDto param);
    public JurisdictionContext.Url.ResultDto updateUrl(JurisdictionContext.Url.ParamDto param);
    public JurisdictionContext.Url.ResultDto deleteUrl(JurisdictionContext.Url.ParamDto param);



    public JurisdictionContext.UrlGroup.ListResultDto urlGroupList(JurisdictionContext.UrlGroup.ParamDto param);
    public JurisdictionContext.UrlGroup.ResultDto createUrlGroup(JurisdictionContext.Url.ParamDto param);
    public JurisdictionContext.UrlGroup.ResultDto updateUrlGroup(JurisdictionContext.Url.ParamDto param);
    public JurisdictionContext.UrlGroup.ResultDto deleteUrlGroup(JurisdictionContext.Url.ParamDto param);
}
