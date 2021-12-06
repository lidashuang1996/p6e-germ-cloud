package club.p6e.germ.cloud.auth.domain.module.sign;

import club.p6e.germ.cloud.auth.controller.TestController;
import club.p6e.germ.cloud.auth.domain.keyvalue.AccountKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.CodeKeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lidashuang
 * @version 1.0
 */
public class SignInPushEntity {

    /** 邮箱类型 */
    private static final String EMAIL_TYPE = "EMAIL";
    /** 电话类型 */
    private static final String PHONE_TYPE = "PHONE";
    /** 注入日志对象 */
    private final Logger LOGGER = LoggerFactory.getLogger(SignInPushEntity.class);

    /** 账号 */
    private final AccountKeyValue account;
    /** 验证码 */
    private final CodeKeyValue verifyCode;

    /**
     * 构造方法初始化
     * @param account 账号
     * @param verifyCode 验证码
     */
    public SignInPushEntity(AccountKeyValue account, CodeKeyValue verifyCode) {
        this.account = account;
        this.verifyCode = verifyCode;
    }

    /**
     * 推送服务
     */
    public void push() {
        final String content = account.getContent();
        if (EMAIL_TYPE.equalsIgnoreCase(account.getType())) {
            pushEmail(content);
        } else if (PHONE_TYPE.equalsIgnoreCase(account.getType())) {
            pushPhone(content);
        }
    }

    /**
     * 推送邮箱
     * @param email 邮箱
     */
    private void pushEmail(String email) {
        LOGGER.info("[ EMAIL ] ==> email: " + email + ", code: " + verifyCode.getContent());
        TestController.setCodeCache(email, verifyCode.getContent());
    }

    /**
     * 推送电话
     * @param phone 电话
     */
    private void pushPhone(String phone) {
        LOGGER.info("[ EMAIL ] ==> phone: " + phone + ", code: " + verifyCode.getContent());
        TestController.setCodeCache(phone, verifyCode.getContent());
    }
}
