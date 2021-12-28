package club.p6e.germ.cloud.console.application.service.impl;

import club.p6e.germ.cloud.console.application.service.JurisdictionManageService;
import club.p6e.germ.cloud.console.controller.support.model.JurisdictionModel;
import club.p6e.germ.cloud.console.domain.aggregate.jurisdiction.JurisdictionPathGroupManageAggregate;
import club.p6e.germ.cloud.console.domain.aggregate.jurisdiction.JurisdictionPathManageAggregate;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.stereotype.Service;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class JurisdictionManageServiceImpl implements JurisdictionManageService {

    @Override
    public JurisdictionModel.Path.ListResultDto pathList(JurisdictionModel.Path.ParamDto param) {
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
        final JurisdictionModel.Path.ListResultDto result = new JurisdictionModel.Path.ListResultDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(P6eCopyUtil.runList(aggregate.getList(), JurisdictionModel.Path.Item.class));
        return result;
    }

    @Override
    public JurisdictionModel.PathGroup.ListResultDto pathGroupList(JurisdictionModel.PathGroup.ParamDto param) {
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
        final JurisdictionModel.PathGroup.ListResultDto result = new JurisdictionModel.PathGroup.ListResultDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(P6eCopyUtil.runList(aggregate.getList(), JurisdictionModel.PathGroup.Item.class));
        return result;
    }
}
