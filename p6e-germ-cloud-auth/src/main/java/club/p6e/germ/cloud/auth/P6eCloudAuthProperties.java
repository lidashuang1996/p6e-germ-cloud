package club.p6e.germ.cloud.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lidashuang
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "p6e.cloud.auth")
public class P6eCloudAuthProperties implements Serializable {

    /** 是否开启 debug */
    private boolean debug = false;

    /** 网站的首页地址 */
    private String websiteHomeUrl = "http://127.0.0.1:9991";
    private String websiteQrCodeUrl = "http://127.0.0.1:9991/qc";

    /** 开启的授权类型 */
    private Oauth2Type[] oauth2Types = new Oauth2Type[] {
            Oauth2Type.CLIENT,
            Oauth2Type.CODE,
            Oauth2Type.PASSWORD
    };

    /** 页面配置 */
    private Page me;
    private Page login;
    private Page register;
    private Page forgetPassword;

    /** 第三方授权登录的配置 */
    private Other qq;
    private Other wechat;

    /** 开放的授权类型 */
    public enum Oauth2Type {
        /**
         * 客户端
         */
        CLIENT,
        /**
         * CODE
         */
        CODE,
        /**
         * 密码
         */
        PASSWORD
    }

    /** 页面配置 */
    @Data
    public static class Page {
        /** 请求路径 */
        private String path;
        /** 资源路径 */
        private String file;
        /** 页面状态 */
        private boolean open = true;
        /** 其他配置 */
        private Map<String, String> content = new HashMap<>();
    }

    /** 其他登录配置 */
    @Data
    public static class Other {
        /** 名称 */
        private String name;
    }
}
