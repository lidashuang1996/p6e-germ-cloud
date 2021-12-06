package club.p6e.germ.cloud.auth.application.service.impl;

import club.p6e.germ.cloud.auth.P6eCloudAuthProperties;
import club.p6e.germ.cloud.auth.application.service.SignService;
import club.p6e.germ.cloud.auth.controller.support.model.SignModel;
import club.p6e.germ.cloud.auth.domain.keyvalue.*;
import club.p6e.germ.cloud.auth.domain.module.RsaEntity;
import club.p6e.germ.cloud.auth.domain.module.VoucherEntity;
import club.p6e.germ.cloud.auth.domain.module.sign.*;
import club.p6e.germ.cloud.auth.domain.service.ServiceFactory;
import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import com.p6e.germ.common.utils.P6eFormatUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class SignServiceImpl implements SignService {

    /** 网站首页的地址 */
    @Resource
    private P6eCloudAuthProperties properties;

    @Override
    public SignModel.Check.ResultDto check(SignModel.Check.ParamDto param) {
        final String type = param.getType();
        final SignModel.Check.ResultDto result = new SignModel.Check.ResultDto();
        final VoucherEntity voucherEntity = new VoucherEntity(new MarkKeyValue(param.getVoucher()));
        if (voucherEntity.isExist()) {
            result.setVoucher(param.getVoucher());
            if (voucherEntity.verifyApType(type)) {
                final RsaEntity rsaEntity = RsaEntity.generate();
                result.setContent(rsaEntity.getPublicKey());
                voucherEntity.setRsa(rsaEntity.getMark().getContent());
            } else if (voucherEntity.verifyAcType(type)) {
                // 查询账号是否存在
                final String account = param.getAccount();
                final boolean bool = P6eFormatUtil.emailVerification(account);
                final UserKeyValue user = ServiceFactory.getUserService().findByAccount(account);
                if (user == null) {
                    result.setError(ErrorModel.ACCOUNT_NOT_EXIST_EXCEPTION);
                } else {
                    final AccountKeyValue accountKeyValue = new AccountKeyValue(account, bool ? "email" : "phone");
                    voucherEntity.setAccount(accountKeyValue);
                    final CodeKeyValue verifyCodeKeyValue =
                            new SignInAccountCodeEntity(accountKeyValue).generate();
                    new SignInPushEntity(accountKeyValue, verifyCodeKeyValue).push();
                }
            } else if (voucherEntity.verifyQcType(type)) {
                final SignInQrCodeEntity qrCodeEntity = SignInQrCodeEntity.generate();
                final CodeKeyValue codeKeyValue = qrCodeEntity.getCode();
                voucherEntity.setQrCode(codeKeyValue);
                result.setContent(this.properties.getWebsiteQrCodeUrl() + "?qrcode=" + codeKeyValue.getContent());
            } else if (voucherEntity.verifyRgType(type)) {
                // 查询账号是否存在
                final String account = param.getAccount();
                final boolean bool = P6eFormatUtil.emailVerification(account);
                final UserKeyValue user = ServiceFactory.getUserService().findByAccount(account);
                if (user == null) {
                    final RsaEntity rsaEntity = RsaEntity.generate();
                    final AccountKeyValue accountKeyValue = new AccountKeyValue(account, bool ? "email" : "phone");
                    voucherEntity.setAccount(accountKeyValue);
                    voucherEntity.setRsa(rsaEntity.getMark().getContent());
                    result.setContent(rsaEntity.getPublicKey());
                } else {
                    result.setError(ErrorModel.ACCOUNT_EXIST_EXCEPTION);
                }
            } else if (voucherEntity.verifyFpType(type)) {
                // 查询账号是否存在
                final String account = param.getAccount();
                final boolean bool = P6eFormatUtil.emailVerification(account);
                final UserKeyValue user = ServiceFactory.getUserService().findByAccount(account);
                if (user == null) {
                    result.setError(ErrorModel.ACCOUNT_NOT_EXIST_EXCEPTION);
                } else {
                    final AccountKeyValue accountKeyValue = new AccountKeyValue(account, bool ? "email" : "phone");
                    final SignForgetPasswordEntity forgetPasswordEntity = SignForgetPasswordEntity.generate(accountKeyValue);
                    final RsaEntity rsaEntity = RsaEntity.generate();
                    forgetPasswordEntity.push();
                    voucherEntity.setAccount(accountKeyValue);
                    voucherEntity.setRsa(rsaEntity.getMark().getContent());
                    result.setContent(rsaEntity.getPublicKey());
                }
            }
        } else {
            result.setError(ErrorModel.VOUCHER_EXPIRE_EXCEPTION);
        }
        return result;
    }


    @Override
    public SignModel.ForgetPassword.ResultDto forgetPassword(SignModel.ForgetPassword.ParamDto param) {
        final SignModel.ForgetPassword.ResultDto result = new SignModel.ForgetPassword.ResultDto();
        final VoucherEntity voucherEntity = new VoucherEntity(new MarkKeyValue(param.getVoucher()));
        if (voucherEntity.isExist()) {
            final AccountKeyValue account = voucherEntity.getAccount();
            final CodeKeyValue code = new CodeKeyValue(param.getCode());
            final SignForgetPasswordEntity forgetPasswordEntity = new SignForgetPasswordEntity(account, code);
            if (forgetPasswordEntity.verification()) {
                final RsaEntity rsaEntity = RsaEntity.generate();
                final String dp = rsaEntity.decryption(param.getPassword());
                forgetPasswordEntity.updatePassword(dp);
            } else {
                result.setError(ErrorModel.VERIFICATION_CODE_EXCEPTION);
            }
        } else {
            result.setError(ErrorModel.VOUCHER_EXPIRE_EXCEPTION);
        }
        return result;
    }

    @Override
    public SignModel.Out.ResultDto out(SignModel.Out.ParamDto param) {
        final SignModel.Out.ResultDto result = new SignModel.Out.ResultDto();
        try {
            SignInTokenEntity.accessToken(param.getToken()).remove();
        } catch (Exception e) {
            result.setError(ErrorModel.TOKEN_EXPIRE_EXCEPTION);
        }
        return result;
    }

    @Override
    public SignModel.Clean.ResultDto clean(SignModel.Clean.ParamDto param) {
        final SignModel.Clean.ResultDto result = new SignModel.Clean.ResultDto();
        try {
            SignInTokenEntity.accessToken(param.getToken()).clean();
        } catch (Exception e) {
            result.setError(ErrorModel.TOKEN_EXPIRE_EXCEPTION);
        }
        return result;
    }

    @Override
    public SignModel.Refresh.ResultDto refresh(SignModel.Refresh.ParamDto param) {
        final SignModel.Refresh.ResultDto result = new SignModel.Refresh.ResultDto();
        final SignInTokenEntity tokenEntity;
        try {
            tokenEntity = SignInTokenEntity.refreshToken(param.getToken()).refresh();
        } catch (Exception e) {
            result.setError(ErrorModel.TOKEN_EXPIRE_EXCEPTION);
            return result;
        }
        final TokenKeyValue token = tokenEntity.getData();
        result.setExpiresIn(SignInTokenEntity.EXPIRES_IN);
        result.setTokenType(SignInTokenEntity.TOKEN_TYPE);
        result.setAccessToken(token.getAccessToken());
        result.setRefreshToken(token.getRefreshToken());
        return result;
    }


}
