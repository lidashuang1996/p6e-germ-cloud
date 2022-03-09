package club.p6e.germ.cloud.console.application.service.impl;

import club.p6e.germ.cloud.console.application.service.JurisdictionManageService;
import club.p6e.germ.cloud.console.controller.support.model.JurisdictionContext;
import club.p6e.germ.cloud.console.domain.aggregate.jurisdiction.JurisdictionPathGroupManageAggregate;
import club.p6e.germ.cloud.console.domain.aggregate.jurisdiction.JurisdictionPathManageAggregate;
import club.p6e.germ.cloud.console.domain.entity.manage.jurisdiction.JurisdictionUrlEntity;
import club.p6e.germ.cloud.console.domain.entity.manage.jurisdiction.JurisdictionUrlGroupEntity;
import club.p6e.germ.cloud.console.infrastructure.model.DictionaryModel;
import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUrlGroupModel;
import club.p6e.germ.cloud.console.infrastructure.model.JurisdictionUrlModel;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.stereotype.Service;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class JurisdictionManageServiceImpl implements JurisdictionManageService {

    @Override
    public JurisdictionContext.Url.ListResultDto urlList(JurisdictionContext.Url.ParamDto param) {
        final JurisdictionPathManageAggregate aggregate;
        if (param == null) {
            aggregate = new JurisdictionPathManageAggregate();
        } else {
            aggregate = new JurisdictionPathManageAggregate(
                    param.getPage(),
                    param.getSize(),
                    param.getSearch()
            );
        }
        final JurisdictionContext.Url.ListResultDto result = new JurisdictionContext.Url.ListResultDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(P6eCopyUtil.runList(aggregate.getList(), JurisdictionContext.Url.Item.class));
        return result;
    }

    @Override
    public JurisdictionContext.Url.ResultDto createUrl(JurisdictionContext.Url.ParamDto param) {
        System.out.println(
                P6eCopyUtil.run(param, JurisdictionUrlModel.class)
        );
        return P6eCopyUtil.run(
                new JurisdictionUrlEntity(
                        P6eCopyUtil.run(param, JurisdictionUrlModel.class)
                ).create().getModel(),
                JurisdictionContext.Url.ResultDto.class
        );
    }

    @Override
    public JurisdictionContext.Url.ResultDto updateUrl(JurisdictionContext.Url.ParamDto param) {
        return P6eCopyUtil.run(
                new JurisdictionUrlEntity(param.getId()).update(
                        P6eCopyUtil.run(param, JurisdictionUrlModel.class)
                ).getModel(),
                JurisdictionContext.Url.ResultDto.class
        );
    }

    @Override
    public JurisdictionContext.Url.ResultDto deleteUrl(JurisdictionContext.Url.ParamDto param) {
        return P6eCopyUtil.run(
                new JurisdictionUrlEntity(param.getId()).delete(param.getOperate()).getModel(),
                JurisdictionContext.Url.ResultDto.class
        );
    }

    @Override
    public JurisdictionContext.UrlGroup.ListResultDto urlGroupList(JurisdictionContext.UrlGroup.ParamDto param) {
        final JurisdictionPathGroupManageAggregate aggregate;
        if (param == null) {
            aggregate = new JurisdictionPathGroupManageAggregate();
        } else {
            aggregate = new JurisdictionPathGroupManageAggregate(
                    param.getPage(),
                    param.getSize(),
                    param.getSearch()
            );
        }
        final JurisdictionContext.UrlGroup.ListResultDto result = new JurisdictionContext.UrlGroup.ListResultDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(P6eCopyUtil.runList(aggregate.getList(), JurisdictionContext.UrlGroup.Item.class));
        return result;
    }

    @Override
    public JurisdictionContext.UrlGroup.ResultDto createUrlGroup(JurisdictionContext.Url.ParamDto param) {
        return P6eCopyUtil.run(
                new JurisdictionUrlGroupEntity(
                        P6eCopyUtil.run(param, JurisdictionUrlGroupModel.class)
                ).create().getModel(),
                JurisdictionContext.UrlGroup.ResultDto.class
        );
    }

    @Override
    public JurisdictionContext.UrlGroup.ResultDto updateUrlGroup(JurisdictionContext.Url.ParamDto param) {
        return P6eCopyUtil.run(
                new JurisdictionUrlGroupEntity(param.getId()).update(
                        P6eCopyUtil.run(param, JurisdictionUrlGroupModel.class)).getModel(),
                JurisdictionContext.UrlGroup.ResultDto.class
        );
    }

    @Override
    public JurisdictionContext.UrlGroup.ResultDto deleteUrlGroup(JurisdictionContext.Url.ParamDto param) {
        return P6eCopyUtil.run(
                new JurisdictionUrlGroupEntity(param.getId()).delete(param.getOperate()).getModel(),
                JurisdictionContext.UrlGroup.ResultDto.class
        );
    }
}
