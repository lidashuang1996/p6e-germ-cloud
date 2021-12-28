package club.p6e.germ.cloud.console.controller.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lidashuang
 * @version 1.0
 */
public class BaseController {

    /** 创建全局 Controller 的日志对象 */
    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /** HTML 返回类型的头 */
    protected static final String CONTENT_TYPE = "Content-Type";
    /** HTML 返回类型的内容 */
    protected static final String HTML_CONTENT_TYPE = "text/html;charset=UTF-8";

    /** 请求携带认证信息的参数 */
    protected static final String AUTH_PARAM_NAME1 = "access_token";
    protected static final String AUTH_PARAM_NAME2 = "accessToken";
    /** 请求头认证的名称 */
    protected static final String AUTH_HEADER_NAME = "Authorization";
    /** 请求头认证的前缀 Bearer */
    protected static final String AUTH_HEADER_BEARER = "Bearer ";
    /** 请求头认证的前缀 Bearer 长度 */
    protected static final int AUTH_HEADER_BEARER_LENGTH = 7;


    /** Oauth2 授权 response ->> CODE */
    protected static String OAUTH2_RESPONSE_TYPE = "CODE";
    /** Oauth2 授权 grant ->> PASSWORD */
    protected static final String OAUTH2_GRANT_PASSWORD_TYPE = "PASSWORD";
    /** Oauth2 授权 grant ->> AUTHORIZATION_CODE */
    protected static final String OAUTH2_GRANT_AUTH_CODE_TYPE = "AUTHORIZATION_CODE";
    /** Oauth2 授权 grant ->> CLIENT_CREDENTIALS */
    protected static final String OAUTH2_GRANT_CLIENT_TYPE = "CLIENT_CREDENTIALS";

    /** 请求参数 验证码 code */
    protected static final String CODE_PARAM = "code";
    /** 请求参数 客户端编号 clientId */
    protected static final String CLIENT_ID_PARAM1 = "client_id";
    protected static final String CLIENT_ID_PARAM2 = "clientId";
    /** 请求参数 客户端密钥 clientSecret */
    protected static final String CLIENT_SECRET_PARAM1 = "client_secret";
    protected static final String CLIENT_SECRET_PARAM2 = "clientSecret";
    /** 请求参数 重定向 URL redirectUri */
    protected static final String REDIRECT_URI_PARAM1 = "redirect_uri";
    protected static final String REDIRECT_URI_PARAM2 = "redirectUri";
    /** 请求参数 资源类型 responseType */
    protected static final String RESPONSE_TYPE_PARAM1 = "response_type";
    protected static final String RESPONSE_TYPE_PARAM2 = "responseType";
    /** 请求参数 资源类型 grantType */
    protected static final String GRANT_TYPE_PARAM1 = "grant_type";
    protected static final String GRANT_TYPE_PARAM2 = "grantType";
    /** 请求参数 作用域 scope */
    protected static final String SCOPE_TYPE_PARAM = "scope";
    /** 请求参数 记号 state */
    protected static final String STATE_TYPE_PARAM = "state";
    /** 请求参数 账号 username */
    protected static final String USERNAME_TYPE_PARAM = "username";
    /** 请求参数 密码 password */
    protected static final String PASSWORD_TYPE_PARAM = "password";

    /** 设备信息的头名称 */
    private static final String DEVICE_HEADER_NAME = "P6e-Device";
    /** 设备信息的头默认内容 */
    private static final String DEVICE_HEADER_DEFAULT_CONTENT = "PC";

    /**
     * 获取基础的请求与的对象 ServletRequestAttributes
     * @return ServletRequestAttributes 返回的对象
     */
    private static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取 HttpServletRequest 对象
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 获取 HttpServletResponse 对象
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    /**
     * 读取请求参数
     * @param names 参数名称
     * @return 参数内容
     */
    protected static String getParameter(String ...names) {
        for (String name : names) {
            final String value = getRequest().getParameter(name);
            if (value != null) {
                return value;
            }
        }
        return null;
    }

    /**
     * 获取用户认证的 ACCESS TOKEN
     * @return ACCESS TOKEN
     */
    protected static String getAccessToken() {
        return getAccessToken(null);
    }

    /**
     * 获取用户认证的 ACCESS TOKEN
     * @param def 默认的 ACCESS TOKEN
     * @return ACCESS TOKEN
     */
    protected static String getAccessToken(String def) {
        final HttpServletRequest request = getRequest();
        final String headerAuthorizationContent = request.getHeader(AUTH_HEADER_NAME);
        if (headerAuthorizationContent != null && headerAuthorizationContent.startsWith(AUTH_HEADER_BEARER)) {
            final String t = headerAuthorizationContent.substring(AUTH_HEADER_BEARER_LENGTH);
            return t.isEmpty() ? def : t;
        }
        final String t = getParameter(AUTH_PARAM_NAME1, AUTH_PARAM_NAME2);
        return t == null ? def : t;
    }

    /** IP */
    private static final String IP_CHAR = ",";
    private static final String IP_UNKNOWN = "UNKNOWN";
    private static final String IP_HEADER_X_FORWARDED_FOR = "X-Forwarded-For";
    private static final String IP_HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String IP_HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private static final String IP_HEADER_HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    private static final String IP_HEADER_HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    private static final String IP_HEADER_X_REAL_IP = "X-Real-IP";
    private static final String IP_DEFAULT_CONTENT = "0.0.0.0";

    /**
     * 获取 IP 的方法
     * @return IP 堵住
     */
    protected static String obtainIp() {
        HttpServletRequest request = getRequest();
        String ip = request.getHeader(IP_HEADER_X_FORWARDED_FOR);
        if (ip != null && !ip.isEmpty() && !IP_UNKNOWN.equalsIgnoreCase(ip)) {
            if (ip.contains(IP_CHAR)) {
                ip = ip.split(IP_CHAR)[0];
            }
        }
        if (ip == null || ip.isEmpty() || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(IP_HEADER_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.isEmpty() || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(IP_HEADER_WL_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.isEmpty() || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(IP_HEADER_HTTP_CLIENT_IP);
        }
        if (ip == null || ip.isEmpty() || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(IP_HEADER_HTTP_X_FORWARDED_FOR);
        }
        if (ip == null || ip.isEmpty() || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader(IP_HEADER_X_REAL_IP);
        }
        if (ip == null || ip.isEmpty() || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip == null ? IP_DEFAULT_CONTENT : ip;
    }

    /**
     * 获取设备信息
     * @return 设备信息
     */
    protected static String getDevice() {
        final String device = getRequest().getHeader(DEVICE_HEADER_NAME);
        return device == null ? DEVICE_HEADER_DEFAULT_CONTENT : device;
    }
}
