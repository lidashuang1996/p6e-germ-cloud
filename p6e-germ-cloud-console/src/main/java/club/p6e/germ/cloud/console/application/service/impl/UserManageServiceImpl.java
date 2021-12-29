package club.p6e.germ.cloud.console.application.service.impl;

import club.p6e.germ.cloud.console.application.service.UserManageService;
import club.p6e.germ.cloud.console.controller.support.model.UserModel;
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
public class UserManageServiceImpl implements UserManageService {

    @Override
    public UserModel.ResultDto update(UserModel.ParamDto param) {
        return P6eCopyUtil.run(new UserManageEntity(param.getId()).update(
                P6eCopyUtil.run(param, club.p6e.germ.cloud.console.infrastructure.model.UserModel.class)
        ).getModel(), UserModel.ResultDto.class);
    }

    @Override
    public UserModel.ResultDto delete(UserModel.ParamDto param) {
        return P6eCopyUtil.run(new UserManageEntity(param.getId()).delete().getModel(), UserModel.ResultDto.class);
    }

    @Override
    public UserModel.ListResultDto list(UserModel.ParamDto param) {
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
        final List<UserModel.Item> rList = new ArrayList<>();
        final UserModel.ListResultDto result = new UserModel.ListResultDto();
        for (final club.p6e.germ.cloud.console.infrastructure.model.UserModel user : aggregate.getList()) {
            rList.add(P6eCopyUtil.run(user, UserModel.Item.class)
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
