package club.p6e.germ.cloud.auth.application.service.impl;

import club.p6e.germ.cloud.auth.application.service.Oauth2AuthService;
import club.p6e.germ.cloud.auth.controller.support.model.Oauth2Model;
import club.p6e.germ.cloud.auth.domain.keyvalue.ClientKeyValue;
import club.p6e.germ.cloud.auth.domain.module.VoucherEntity;
import club.p6e.germ.cloud.auth.domain.module.oauth2.Oauth2ClientEntity;
import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import club.p6e.germ.cloud.auth.domain.keyvalue.DeviceKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.MarkKeyValue;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class Oauth2AuthServiceImpl implements Oauth2AuthService {

    @Override
    public Oauth2Model.Code.ResultDto code(Oauth2Model.Code.ParamDto param) {
        final Oauth2ClientEntity clientEntity;
        final Oauth2Model.Code.ResultDto result = new Oauth2Model.Code.ResultDto();
        try {
            clientEntity = new Oauth2ClientEntity(param.getClientId());
        } catch (Exception e) {
            // 忽略
            result.setError(ErrorModel.CLIENT_ID_PARAMETER_EXCEPTION);
            return result;
        }
        // 验证作用域
        if (clientEntity.verifyScope(param.getScope())) {
            // 验证的回调地址
            if (clientEntity.verifyRedirectUri(param.getRedirectUri())) {
                // 读取客户端的信息
                final ClientKeyValue client = clientEntity.getClient();
                final Map<String, String> map = new HashMap<>(16);
                map.put("clientId", client.getClientId());
                map.put("clientName", client.getClientName());
                map.put("clientClientIcon", client.getClientIcon());
                map.put("clientClientDescribe", client.getClientDescribe());
                final VoucherEntity voucherEntity = VoucherEntity.generate();
                voucherEntity.setMap(map);
                voucherEntity.setOauth2("1");
                voucherEntity.setState(param.getState());
                voucherEntity.setRedirectUri(param.getRedirectUri());
                voucherEntity.setDevice(new DeviceKeyValue("PC"));
                // 写入返回的数据
                result.setVoucher(voucherEntity.getVoucher().getContent());
            } else {
                result.setError(ErrorModel.CLIENT_REDIRECT_URI_PARAMETER_EXCEPTION);
            }
        } else {
            result.setError(ErrorModel.CLIENT_SCOPE_PARAMETER_EXCEPTION);
        }
        return result;
    }

    @Override
    public Oauth2Model.Confirm.ResultDto confirm(Oauth2Model.Confirm.ParamDto param) {
        final Oauth2Model.Confirm.ResultDto result = new Oauth2Model.Confirm.ResultDto();
        final VoucherEntity voucherEntity = new VoucherEntity(new MarkKeyValue(param.getVoucher()));
        if (voucherEntity.isExist()) {
            if (voucherEntity.getOauth2Confirm() == null) {
                result.setError(ErrorModel.PARAMETER_EXCEPTION);
            } else {
                result.setState(voucherEntity.getState());
                result.setRedirectUri(voucherEntity.getRedirectUri());
            }
        } else {
            result.setError(ErrorModel.VOUCHER_EXPIRE_EXCEPTION);
        }
        return result;
    }

}
