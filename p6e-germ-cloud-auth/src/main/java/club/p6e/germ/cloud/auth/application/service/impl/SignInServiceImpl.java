package club.p6e.germ.cloud.auth.application.service.impl;

import club.p6e.germ.cloud.auth.P6eCloudAuthProperties;
import club.p6e.germ.cloud.auth.application.service.SignInService;
import club.p6e.germ.cloud.auth.controller.support.model.SignModel;
import club.p6e.germ.cloud.auth.domain.keyvalue.*;
import club.p6e.germ.cloud.auth.domain.module.RsaEntity;
import club.p6e.germ.cloud.auth.domain.module.VoucherEntity;
import club.p6e.germ.cloud.auth.domain.module.sign.*;
import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import com.p6e.germ.common.utils.P6eFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Service
public class SignInServiceImpl implements SignInService {


    private static final Logger LOGGER = LoggerFactory.getLogger(SignInServiceImpl.class);

    @Resource
    private P6eCloudAuthProperties properties;


    @Override
    public SignModel.In.ResultDto in(SignModel.In.ParamDto param) {
        final SignModel.In.ResultDto result = new SignModel.In.ResultDto();
        final VoucherEntity voucherEntity =
                new VoucherEntity(new MarkKeyValue(param.getVoucher()));
        if (voucherEntity.isExist()) {
            final String account = param.getAccount();
            final boolean bool = P6eFormatUtil.emailVerification(account);
            final DeviceKeyValue device = voucherEntity.getDevice();
            final AccountKeyValue accountKeyValue = new AccountKeyValue(account, bool ? "email" : "phone");
            final RsaEntity rsaEntity = new RsaEntity(voucherEntity.getRsa());
            final SignInAccountPasswordEntity accountPasswordEntity;
            try {
                accountPasswordEntity = new SignInAccountPasswordEntity(accountKeyValue);
            } catch (Exception e) {
                // 忽略异常
                result.setError(ErrorModel.ACCOUNT_OR_PASSWORD_EXCEPTION);
                return result;
            }
            if (accountPasswordEntity.verifyPassword(new PasswordKeyValue(rsaEntity.decryption(param.getPassword())))) {
                final TokenKeyValue tokenKeyValue =
                        SignInTokenEntity.generate(accountPasswordEntity.getUser(), device).getData();
                resultHandle(tokenKeyValue, voucherEntity, result);
            } else {
                result.setError(ErrorModel.ACCOUNT_OR_PASSWORD_EXCEPTION);
            }
        } else {
            result.setError(ErrorModel.VOUCHER_EXPIRE_EXCEPTION);
        }
        return result;
    }

    @Override
    public SignModel.Code.ResultDto code(SignModel.Code.ParamDto param) {
        final SignModel.Code.ResultDto result = new SignModel.Code.ResultDto();
        final VoucherEntity voucherEntity =
                new VoucherEntity(new MarkKeyValue(param.getVoucher()));
        if (voucherEntity.isExist()) {
            final AccountKeyValue account = voucherEntity.getAccount();
            if (account == null || !account.getContent().equals(param.getAccount())) {
                result.setError(ErrorModel.PARAMETER_EXCEPTION);
            } else {
                final DeviceKeyValue device = voucherEntity.getDevice();
                final SignInAccountCodeEntity accountCodeEntity = new SignInAccountCodeEntity(account);
                if (accountCodeEntity.verifyCode(new CodeKeyValue(param.getCode()))) {
                    final TokenKeyValue tokenKeyValue =
                            SignInTokenEntity.generate(accountCodeEntity.getUser(), device).getData();
                    resultHandle(tokenKeyValue, voucherEntity, result);
                } else {
                    result.setError(ErrorModel.VERIFICATION_CODE_EXCEPTION);
                }
            }
        } else {
            result.setError(ErrorModel.VOUCHER_EXPIRE_EXCEPTION);
        }
        return result;
    }

    @Override
    public SignModel.QrCode.ResultDto qrCode(SignModel.QrCode.ParamDto param) {
        final SignModel.QrCode.ResultDto result = new SignModel.QrCode.ResultDto();
        final VoucherEntity voucherEntity =
                new VoucherEntity(new MarkKeyValue(param.getVoucher()));
        if (voucherEntity.isExist()) {
            final CodeKeyValue code = voucherEntity.getQrCode();
            final SignInQrCodeEntity qrCodeEntity = new SignInQrCodeEntity(code);
            if (qrCodeEntity.isExist()) {
                final UserKeyValue user = qrCodeEntity.getUser();
                if (user != null) {
                    final DeviceKeyValue device = voucherEntity.getDevice();
                    final TokenKeyValue tokenKeyValue = SignInTokenEntity.generate(user, device).getData();
                    result.setExist(true);
                    resultHandle(tokenKeyValue, voucherEntity, result);
                }
            } else {
                result.setError(ErrorModel.QR_CODE_EXPIRE_EXCEPTION);
            }
        } else {
            result.setError(ErrorModel.VOUCHER_EXPIRE_EXCEPTION);
        }
        return result;
    }

    @Override
    public SignModel.Verification.ResultDto verification(SignModel.Verification.ParamDto param) {
        final SignModel.Verification.ResultDto result = new SignModel.Verification.ResultDto();
        final VoucherEntity voucherEntity =
                new VoucherEntity(new MarkKeyValue(param.getVoucher()));
        if (voucherEntity.isExist()) {
            try {
                final SignInTokenEntity tokenEntity = SignInTokenEntity.accessToken(param.getAccessToken());
                resultHandle(tokenEntity.getData(), voucherEntity, result);
            } catch (Exception e) {
                result.setError(ErrorModel.TOKEN_EXPIRE_EXCEPTION);
            }
        } else {
            result.setError(ErrorModel.VOUCHER_EXPIRE_EXCEPTION);
        }
        return result;
    }

    @Override
    public SignModel.Qq.ResultDto qq(SignModel.Qq.ParamDto param) {
        final SignModel.Qq.ResultDto result = new SignModel.Qq.ResultDto();
        final String voucher = param.getVoucher();
        final VoucherEntity voucherEntity = new VoucherEntity(new MarkKeyValue(voucher));
        if (voucherEntity.isExist()) {
            final SignInOtherStateEntity otherEntity = SignInOtherStateEntity.generate(OtherStateTypeKeyValue.QQ, voucher);
            result.setContent(
                    properties.getQq().getName() + "&state=" +  otherEntity.getType().name() + "_" + otherEntity.getMark().getContent()
            );
        } else {
            result.setError(ErrorModel.VOUCHER_EXPIRE_EXCEPTION);
        }
        return result;
    }

    @Override
    public SignModel.QqCallback.ResultDto qqCallback(SignModel.QqCallback.ParamDto param) {
        final SignModel.QqCallback.ResultDto result = new SignModel.QqCallback.ResultDto();
        try {
            final SignInOtherStateEntity otherEntity =
                    new SignInOtherStateEntity(OtherStateTypeKeyValue.QQ, new MarkKeyValue(param.getState()));
            // 验证 QQ 的 CODE
            // ..
            // ..
            final UserKeyValue user = new UserKeyValue();
            user.setId("1");
            user.setAccount("admin@qq.com");
            user.setPassword("123456");
            LOGGER.info("验证 QQ 的 CODE --> " + param.getCode() + "  " + user);
            final String voucher = otherEntity.getCache();
            final VoucherEntity voucherEntity = new VoucherEntity(new MarkKeyValue(voucher));
            if (voucherEntity.isExist()) {
                final DeviceKeyValue device = voucherEntity.getDevice();
                final SignInTokenEntity tokenEntity = SignInTokenEntity.generate(user, device);
                resultHandle(tokenEntity.getData(), voucherEntity, result);
            } else {
                result.setError(ErrorModel.VOUCHER_EXPIRE_EXCEPTION);
            }
        } catch (Exception e) {
            result.setError(ErrorModel.OTHER_LOGIN_STATE_NOT_EXIST);
        }
        return result;
    }

    @Override
    public SignModel.Wechat.ParamDto wechat(SignModel.Wechat.ParamDto param) {
        return null;
    }

    @Override
    public SignModel.WechatCallback.ParamDto wechatCallback(SignModel.WechatCallback.ParamDto param) {
        return null;
    }

    /**
     * 统一对放回结果处理
     * @param token 用户凭证
     * @param result 结果返回对象
     */
    private void resultHandle(TokenKeyValue token, VoucherEntity voucherEntity, SignModel.In.ResultDto result) {
        result.setExpiresIn(SignInTokenEntity.EXPIRES_IN);
        result.setTokenType(SignInTokenEntity.TOKEN_TYPE);
        result.setAccessToken(token.getAccessToken());
        result.setRefreshToken(token.getRefreshToken());
        if (voucherEntity.getOauth2() != null) {
            voucherEntity.setOauth2Confirm("1");
            final Map<String, String> data = voucherEntity.getData();
            final Map<String, String> client = new HashMap<>(4);
            client.put("clientId", data.get("clientId"));
            client.put("clientName", data.get("clientName"));
            client.put("clientIcon", data.get("clientClientIcon"));
            client.put("clientDescribe", data.get("clientClientDescribe"));
            result.setClient(client);
        }
    }
}
