package club.p6e.germ.cloud.auth.controller;

import club.p6e.germ.cloud.auth.P6eCloudAuthProperties;
import club.p6e.germ.cloud.auth.controller.support.ApiResultModel;
import club.p6e.germ.cloud.auth.controller.support.BaseController;
import club.p6e.germ.cloud.auth.controller.support.model.VoucherModel;
import club.p6e.germ.cloud.auth.domain.keyvalue.CodeKeyValue;
import club.p6e.germ.cloud.auth.domain.module.sign.SignInQrCodeEntity;
import club.p6e.germ.cloud.auth.infrastructure.model.ErrorModel;
import club.p6e.germ.cloud.auth.application.service.VoucherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    private static final Map<String, String> CODE_CACHE = new HashMap<>(16);

    public static void delCodeCache(String account) {
        synchronized (CODE_CACHE) {
            CODE_CACHE.remove(account);
        }

    }
    public synchronized static void setCodeCache(String account, String code) {
        synchronized (CODE_CACHE) {
            CODE_CACHE.put(account, code);
        }
    }

    @Resource
    private VoucherService voucherService;

    @Resource
    private P6eCloudAuthProperties properties;

    @RequestMapping("/code")
    public ApiResultModel code(String account) {
        if (properties.isDebug()) {
            final String code = CODE_CACHE.get(account);
            delCodeCache(account);
            return ApiResultModel.build(code);
        } else {
            return ApiResultModel.build(ErrorModel.SERVICE_EXCEPTION);
        }
    }

    @RequestMapping("/qrcode")
    public Object qrcode(String qrcode) {
        if (properties.isDebug()) {
            final SignInQrCodeEntity qrCodeEntity = new SignInQrCodeEntity(new CodeKeyValue(qrcode));
            if (qrCodeEntity.isExist()) {
                qrCodeEntity.setUser("1");
                return ApiResultModel.build();
            } else {
                return ApiResultModel.build(ErrorModel.QR_CODE_EXPIRE_EXCEPTION);
            }
        } else {
            return ApiResultModel.build(ErrorModel.SERVICE_EXCEPTION);
        }
    }

    @GetMapping("/voucher")
    public ApiResultModel def() {
        if (properties.isDebug()) {
            return ApiResultModel.build(voucherService.create(new VoucherModel.ParamDto().setDevice(getDevice())));
        } else {
            return ApiResultModel.build(ErrorModel.SERVICE_EXCEPTION);
        }
    }
}
