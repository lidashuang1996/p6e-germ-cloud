package club.p6e.germ.cloud.auth.domain.module.sign;

import club.p6e.germ.cloud.auth.domain.service.ServiceFactory;
import club.p6e.germ.cloud.auth.domain.keyvalue.AccountKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.PasswordKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.UserKeyValue;

/**
 * 登录-账号密码登录的实体
 * @author lidashuang
 * @version 1.0
 */
public class SignInAccountPasswordEntity {

    /** 用户的数据 */
    private final UserKeyValue user;
    /** 账号 */
    private final AccountKeyValue account;

    /**
     * 构造函数初始化
     * @param account 账号键值对
     */
    public SignInAccountPasswordEntity(AccountKeyValue account) {
        this.account = account;
        this.user = ServiceFactory.getUserService().findByAccount(account.getContent());
        if (this.user == null) {
            throw new RuntimeException(this.getClass() + " account data is null.");
        }
    }

    /**
     * 验证该密码是否和账号匹配
     * @param password 密码键值对
     * @return 是否匹配成功
     */
    public boolean verifyPassword(PasswordKeyValue password) {
        return user.getPassword().equals(ServiceFactory.getUserService().passwordEncryption(password.getContent()));
    }

    /**
     * 读取账号
     * @return 账号键值对
     */
    public AccountKeyValue getAccount() {
        return account;
    }

    /**
     * 用户数据
     * @return 用户数据信息
     */
    public UserKeyValue getUser() {
        return user;
    }

}
