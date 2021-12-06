package club.p6e.germ.cloud.auth.application.service.impl;

import club.p6e.germ.cloud.auth.controller.support.model.SignModel;
import club.p6e.germ.cloud.auth.domain.keyvalue.AccountKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.MarkKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.PasswordKeyValue;
import club.p6e.germ.cloud.auth.domain.module.RsaEntity;
import club.p6e.germ.cloud.auth.domain.module.VoucherEntity;
import club.p6e.germ.cloud.auth.domain.module.sign.SignUpEntity;
import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import club.p6e.germ.cloud.auth.application.service.SignUpService;
import org.springframework.stereotype.Service;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class SignUpServiceImpl implements SignUpService {

    @Override
    public SignModel.Up.ResultDto up(SignModel.Up.ParamDto param) {
        final SignModel.Up.ResultDto result = new SignModel.Up.ResultDto();
        final VoucherEntity voucherEntity =
                new VoucherEntity(new MarkKeyValue(param.getVoucher()));
        if (voucherEntity.isExist()) {
            final String decryptionPassword;
            try {
                decryptionPassword = new RsaEntity(voucherEntity.getRsa()).decryption(param.getPassword());
            } catch (Exception e) {
                result.setError(ErrorModel.RAS_EXPIRE_EXCEPTION);
                return result;
            }
            final AccountKeyValue account = voucherEntity.getAccount();
            final PasswordKeyValue password = new PasswordKeyValue(decryptionPassword);
            try {
                new SignUpEntity(account, password);
            } catch (Exception e) {
                result.setError(ErrorModel.ACCOUNT_EXIST_EXCEPTION);
                return result;
            }
        } else {
            result.setError(ErrorModel.VOUCHER_EXPIRE_EXCEPTION);
        }
        return result;
    }

}
