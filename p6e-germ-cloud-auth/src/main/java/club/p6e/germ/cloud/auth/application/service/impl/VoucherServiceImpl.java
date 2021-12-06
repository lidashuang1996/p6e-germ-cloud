package club.p6e.germ.cloud.auth.application.service.impl;

import club.p6e.germ.cloud.auth.controller.support.model.VoucherModel;
import club.p6e.germ.cloud.auth.domain.keyvalue.DeviceKeyValue;
import club.p6e.germ.cloud.auth.domain.module.VoucherEntity;
import club.p6e.germ.cloud.auth.application.service.VoucherService;
import org.springframework.stereotype.Service;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class VoucherServiceImpl implements VoucherService {

    @Override
    public VoucherModel.ResultDto create(VoucherModel.ParamDto param) {
        VoucherEntity voucherEntity = VoucherEntity.generate();
        voucherEntity.setDevice(new DeviceKeyValue(param.getDevice()));
        return new VoucherModel.ResultDto().setVoucher(voucherEntity.getVoucher().getContent());
    }

}
