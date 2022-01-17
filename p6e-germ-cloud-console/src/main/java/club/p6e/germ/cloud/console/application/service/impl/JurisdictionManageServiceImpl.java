package club.p6e.germ.cloud.console.application.service.impl;

import club.p6e.germ.cloud.console.application.service.JurisdictionManageService;
import club.p6e.germ.cloud.console.controller.support.model.JurisdictionContext;
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
    public JurisdictionContext.Path.ListResultDto pathList(JurisdictionContext.Path.ParamDto param) {
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
        final JurisdictionContext.Path.ListResultDto result = new JurisdictionContext.Path.ListResultDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(P6eCopyUtil.runList(aggregate.getList(), JurisdictionContext.Path.Item.class));
        return result;
    }

    @Override
    public JurisdictionContext.PathGroup.ListResultDto pathGroupList(JurisdictionContext.PathGroup.ParamDto param) {
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
        final JurisdictionContext.PathGroup.ListResultDto result = new JurisdictionContext.PathGroup.ListResultDto();
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        result.setList(P6eCopyUtil.runList(aggregate.getList(), JurisdictionContext.PathGroup.Item.class));
        return result;
    }
}
