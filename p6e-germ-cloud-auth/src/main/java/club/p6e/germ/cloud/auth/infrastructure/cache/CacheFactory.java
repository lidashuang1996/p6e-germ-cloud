package club.p6e.germ.cloud.auth.infrastructure.cache;

import com.p6e.germ.common.utils.P6eSpringUtil;

/**
 * @author lidashuang
 * @version 1.0
 */
public class CacheFactory {

    private static final SignInAccountCodeCache SIGN_IN_ACCOUNT_CODE_CACHE ;
    private static final SignInRsaCache SIGN_IN_RSA_CACHE;
    private static final VoucherCache VOUCHER_CACHE;
    private static final SignUserTokenCache SIGN_USER_TOKEN_CACHE;
    private static final SignInQrCodeCache SIGN_IN_QR_CODE_CACHE;
    private static final SignInOtherStateCache SIGN_IN_OTHER_CODE_CACHE;

    static {
        SIGN_IN_ACCOUNT_CODE_CACHE = P6eSpringUtil.getBean(SignInAccountCodeCache.class);
        SIGN_IN_RSA_CACHE = P6eSpringUtil.getBean(SignInRsaCache.class);
        VOUCHER_CACHE = P6eSpringUtil.getBean(VoucherCache.class);
        SIGN_USER_TOKEN_CACHE = P6eSpringUtil.getBean(SignUserTokenCache.class);
        SIGN_IN_QR_CODE_CACHE = P6eSpringUtil.getBean(SignInQrCodeCache.class);
        SIGN_IN_OTHER_CODE_CACHE = P6eSpringUtil.getBean(SignInOtherStateCache.class);
    }

    public static SignInAccountCodeCache getSignInAccountCodeCache() {
        return SIGN_IN_ACCOUNT_CODE_CACHE;
    }

    public static VoucherCache getVoucherCache() {
        return VOUCHER_CACHE;
    }

    public static SignInRsaCache getSignInRsaCache() {
        return SIGN_IN_RSA_CACHE;
    }

    public static SignUserTokenCache getSignUserTokenCache() {
        return SIGN_USER_TOKEN_CACHE;
    }

    public static SignInQrCodeCache getSignInQrCodeCache() {
        return SIGN_IN_QR_CODE_CACHE;
    }

    public static SignInOtherStateCache getSignInOtherCodeCache() {
        return SIGN_IN_OTHER_CODE_CACHE;
    }
}
