package club.p6e.germ.cloud.jurisdiction.verification;

/**
 * 验证服务
 * @author lidashuang
 * @version 1.0
 */
public interface VerificationService {

    /**
     * 执行验证是否拥有权限服务
     * @param uid 用户 UID
     * @param path 请求路径
     * @param method 请求方法
     * @return 权限服务结果
     */
    public boolean execute(Integer uid, String path, String method);

}
