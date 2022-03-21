package club.p6e.germ.cloud.console.application.service.impl;

import club.p6e.germ.cloud.console.application.service.ManageUserService;
import club.p6e.germ.cloud.console.controller.support.model.UserContext;
import club.p6e.germ.cloud.console.domain.aggregate.manage.user.UserManageAggregate;
import club.p6e.germ.cloud.console.domain.entity.manage.user.UserManageEntity;
import com.p6e.germ.common.utils.P6eCopyUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class UserManageServiceImpl implements ManageUserService {

    @Override
    public UserContext.ResultDto update(UserContext.ParamDto param) {
        return P6eCopyUtil.run(new UserManageEntity(param.getId()).update(
                P6eCopyUtil.run(param, club.p6e.germ.cloud.console.infrastructure.model.UserModel.class)
        ).getModel(), UserContext.ResultDto.class);
    }

    @Override
    public UserContext.ResultDto delete(UserContext.ParamDto param) {
        return P6eCopyUtil.run(new UserManageEntity(param.getId()).delete().getModel(), UserContext.ResultDto.class);
    }

    @Override
    public UserContext.ListResultDto list(UserContext.ParamDto param) {
        final UserManageAggregate aggregate;
        if (param == null) {
            aggregate = new UserManageAggregate();
        } else {
            aggregate = new UserManageAggregate(
                    param.getPage(),
                    param.getSize(),
                    param.getType(),
                    param.getSearch()
            );
        }
        final List<UserContext.Item> rList = new ArrayList<>();
        final UserContext.ListResultDto result = new UserContext.ListResultDto();
        for (final club.p6e.germ.cloud.console.infrastructure.model.UserModel user : aggregate.getList()) {
            rList.add(P6eCopyUtil.run(user, UserContext.Item.class)
                    .setQq(user.getAuth().getQq() == null ? "0" : "1")
                    .setWechat(user.getAuth().getWechat() == null ? "0" : "1")
                    .setPhone(user.getAuth().getPhone())
                    .setEmail(user.getAuth().getEmail()));
        }
        result.setList(rList);
        result.setPage(aggregate.getPage());
        result.setSize(aggregate.getSize());
        result.setTotal(aggregate.getTotal());
        return result;
    }

}
