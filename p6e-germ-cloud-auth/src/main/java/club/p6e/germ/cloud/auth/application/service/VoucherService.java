package club.p6e.germ.cloud.auth.application.service;

import club.p6e.germ.cloud.auth.controller.support.model.VoucherModel;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface VoucherService {

    /**
     * 创建凭证信息
     * @param param 参数对象
     * @return 结果对象
     */
    public VoucherModel.ResultDto create(VoucherModel.ParamDto param);

}
