package club.p6e.germ.cloud.auth.domain.module.sign;
import club.p6e.germ.cloud.auth.infrastructure.cache.CacheFactory;
import club.p6e.germ.cloud.auth.domain.service.ServiceFactory;
import club.p6e.germ.cloud.auth.domain.keyvalue.AccountKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.UserKeyValue;
import club.p6e.germ.cloud.auth.domain.keyvalue.CodeKeyValue;
import com.p6e.germ.common.utils.P6eGeneratorUtil;

import java.util.List;

/**
 * 登录-账号验证码登录的实体
 * @author lidashuang
 * @version 1.0
 */
public class SignInAccountCodeEntity {

    /** 用户的数据 */
    private final UserKeyValue user;
    /** 账号 */
    private final AccountKeyValue account;

    /**
     * 构造函数初始化
     * @param account 账号键值对
     */
    public SignInAccountCodeEntity(AccountKeyValue account) {
        this.account = account;
        this.user = ServiceFactory.getUserService().findByAccount(account.getContent());
        if (this.user == null) {
            throw new RuntimeException(this.getClass() + " account data is null.");
        }
    }

    /**
     * 生成一个该账号的验证码
     * @return 验证码键值对
     */
    public CodeKeyValue generate() {
        final String code = P6eGeneratorUtil.random();
        CacheFactory.getSignInAccountCodeCache().set(account.getContent(), code);
        return new CodeKeyValue(code);
    }

    /**
     * 验证该验证码是否和账号匹配
     * @param code 验证码键值对
     * @return 是否匹配成功
     */
    public boolean verifyCode(CodeKeyValue code) {
        final List<String> codes = CacheFactory.getSignInAccountCodeCache().get(account.getContent());
        return codes.contains(code.getContent());
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
