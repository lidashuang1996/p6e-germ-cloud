package club.p6e.germ.cloud.auth.domain.module;

import club.p6e.germ.cloud.auth.domain.keyvalue.AccountKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.CodeKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.DeviceKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.MarkKeyValue;
import club.p6e.germ.cloud.auth.infrastructure.cache.CacheFactory;
import com.p6e.germ.common.utils.P6eGeneratorUtil;
import com.p6e.germ.common.utils.P6eJsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
public class VoucherEntity {

    private static final String AP = "AP";
    private static final String AC = "AC";
    private static final String QC = "QC";
    private static final String RG = "RG";
    private static final String FP = "FP";

    private static final String RSA = "RSA";
    private static final String QR_CODE = "QR_CODE";
    private static final String DEVICE = "DEVICE";
    private static final String ACCOUNT = "ACCOUNT";
    private static final String ACCOUNT_TYPE = "ACCOUNT_TYPE";


    private MarkKeyValue voucher;
    private Map<String, String> data;

    public static VoucherEntity generate() {
        final String uuid = P6eGeneratorUtil.uuid();
        CacheFactory.getVoucherCache().set(uuid, P6eJsonUtil.toJson(new HashMap<>(0)));
        return new VoucherEntity(new MarkKeyValue(uuid));
    }

    public VoucherEntity(MarkKeyValue voucher) {
        final String content = CacheFactory.getVoucherCache().get(voucher.getContent());
        if (content != null) {
            this.voucher = voucher;
            this.data = P6eJsonUtil.fromJsonToMap(content, String.class, String.class);
        }
    }

    public boolean verifyApType(String type) {
        return AP.equalsIgnoreCase(type);
    }
    public boolean verifyAcType(String type) {
        return AC.equalsIgnoreCase(type);
    }
    public boolean verifyQcType(String type) {
        return QC.equalsIgnoreCase(type);
    }

    public boolean verifyRgType(String type) {
        return RG.equalsIgnoreCase(type);
    }

    public boolean verifyFpType(String type) {
        return FP.equalsIgnoreCase(type);
    }

    public boolean isExist() {
        return data != null;
    }

    public Map<String, String> getData() {
        return data;
    }

    public MarkKeyValue getVoucher() {
        return voucher;
    }

    public MarkKeyValue getRsa() {
        return new MarkKeyValue(data.get(RSA));
    }

    public AccountKeyValue getAccount() {
        final String account = data.get(ACCOUNT);
        final String accountType = data.get(ACCOUNT_TYPE);
        if (account == null || accountType == null) {
            return null;
        } else {
            return new AccountKeyValue(account, accountType);
        }
    }

    public String getState() {
       return data.get("state");
    }


    public void setState(String state) {
        data.put("state", state);
    }

    public String getRedirectUri() {
        return data.get("redirectUri");
    }


    public void setRedirectUri(String state) {
        data.put("redirectUri", state);
    }

    public void setMap(Map<String, String> map) {
        data.putAll(map);
        CacheFactory.getVoucherCache().set(voucher.getContent(), P6eJsonUtil.toJson(data));
    }

    public void setOauth2(String status) {
        data.put("oauth2", status);
        CacheFactory.getVoucherCache().set(voucher.getContent(), P6eJsonUtil.toJson(data));
    }


    public void setOauth2Confirm(String status) {
        data.put("oauth2Confirm", status);
        CacheFactory.getVoucherCache().set(voucher.getContent(), P6eJsonUtil.toJson(data));
    }

    public String getOauth2Confirm() {
        return data.get("oauth2Confirm");
    }

    public String getOauth2() {
        return data.get("oauth2");
    }

    public DeviceKeyValue getDevice() {
        return new DeviceKeyValue(data.get(DEVICE));
    }

    public void setAccount(AccountKeyValue account) {
        data.put(ACCOUNT, account.getContent());
        data.put(ACCOUNT_TYPE, account.getType());
        CacheFactory.getVoucherCache().set(voucher.getContent(), P6eJsonUtil.toJson(data));
    }

    public void setDevice(DeviceKeyValue device) {
        data.put(DEVICE, device.getContent());
        CacheFactory.getVoucherCache().set(voucher.getContent(), P6eJsonUtil.toJson(data));
    }

    public void setRsa(String rsa) {
        data.put(RSA, rsa);
        CacheFactory.getVoucherCache().set(voucher.getContent(), P6eJsonUtil.toJson(data));
    }

    public void setQrCode(CodeKeyValue code) {
        data.put(QR_CODE, code.getContent());
        CacheFactory.getVoucherCache().set(voucher.getContent(), P6eJsonUtil.toJson(data));
    }

    public CodeKeyValue getQrCode() {
        return new CodeKeyValue(data.get(QR_CODE));
    }
}
