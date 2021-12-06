package club.p6e.germ.cloud.auth.domain.service;

import club.p6e.germ.cloud.auth.domain.keyvalue.UserKeyValue;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author lidashuang
 * @version 1.0
 */
public interface UserService {

    /**
     * 根据账号查询用户信息
     * @param id 账号
     * @return 用户的对象
     */
    public UserKeyValue findById(Integer id);

    /**
     * 根据账号查询用户信息
     * @param account 账号
     * @return 用户的对象
     */
    public UserKeyValue findByAccount(String account);

    /**
     * 创建账号
     * @param account 账号
     * @param code  授权编码
     * @param type 授权类型
     * @return 用户的对象
     */
    public UserKeyValue create(String account, String code, String type);

    /**
     * 创建账号
     * @param account 账号
     * @param password 密码
     * @return 用户的对象
     */
    public UserKeyValue create(String account, String password);

    /**
     * 修改密码
     * @param account 账号
     * @param password 密码
     * @return 用户的对象
     */
    public UserKeyValue updatePassword(String account, String password);

    /**
     * 密码加密
     * @param password 待加密的内容
     * @return 解密的内容
     */
    public default String passwordEncryption(String password) {
        final String data = DigestUtils.md5DigestAsHex(password.getBytes());
        return DigestUtils.md5DigestAsHex(data.substring(8).getBytes(StandardCharsets.UTF_8)) + data.substring(0, 8);
    }
}
