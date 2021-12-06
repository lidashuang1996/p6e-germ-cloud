package club.p6e.germ.cloud.auth.domain.service;

import com.p6e.germ.common.utils.P6eSpringUtil;

/**
 * 领域服务的工厂
 * @author lidashuang
 * @version 1.0
 */
public class ServiceFactory {

    private static UserService USER_SERVICE;
    private static Oauth2ClientService OAUTH2_CLIENT_SERVICE;

    /**
     * 获取用户服务
     * @return 服务对象
     */
    public static UserService getUserService() {
        if (USER_SERVICE == null) {
            createUserService();
        }
        return USER_SERVICE;
    }

    public synchronized static void createUserService() {
        USER_SERVICE = P6eSpringUtil.getBean(UserService.class, new UserServiceLocalImpl());
    }

    /**
     * 获取认证客户端服务
     * @return 服务对象
     */
    public static Oauth2ClientService getOauth2ClientService() {
        if (OAUTH2_CLIENT_SERVICE == null) {
            createOauth2ClientService();
        }
        return OAUTH2_CLIENT_SERVICE;
    }

    public synchronized static void createOauth2ClientService() {
        OAUTH2_CLIENT_SERVICE = P6eSpringUtil.getBean(Oauth2ClientService.class, new Oauth2ClientServiceLocalImpl());
    }

}
