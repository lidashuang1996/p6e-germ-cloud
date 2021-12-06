package club.p6e.germ.message.http;

/**
 * @author lidashuang
 * @version 1.0
 */
public enum HttpErrorModel {
/**
 */
PAGE_EXPIRED(1),
/**
 * 资源不存在
 */
RESOURCES_NO_EXIST_EXCEPTION(1),
    /**
     * 资源存在关联数据 relation
     */
    RESOURCES_EXIST_RELATION_EXCEPTION(111),
/**
 * DB 操作异常
 */
DATABASE_EXCEPTION(2),
/**
 * 参数异常
 */
PARAMETER_EXCEPTION(3),
    /**
     * 邮件/SMS 异常详细
     */
    MESSAGE_RECIPIENTS_NULL_PARAMETER_EXCEPTION(300),
    MESSAGE_RECIPIENTS_LENGTH_OVERFLOW_PARAMETER_EXCEPTION(300),
    MESSAGE_TEMPLATE_ID_NULL_PARAMETER_EXCEPTION(300),


/** 客户端--参数异常 */
CLIENT_ID_PARAMETER_EXCEPTION(3),
CLIENT_REDIRECT_URI_PARAMETER_EXCEPTION(3),
CLIENT_RESPONSE_TYPE_EXCEPTION(3),
CLIENT_SCOPE_EXCEPTION(3),

/**
 * 认证类型没有启动
 */
OAUTH2_AUTH_TYPE_NO_ENABLE(55),

/**
 * 过期异常
 */
EXPIRATION_EXCEPTION(4),
/**
 * 服务异常
 */
SERVICE_EXCEPTION(5),
/**
 * 账号密码
 */
ACCOUNT_OR_PASSWORD(6),
/**
 * 方法异常
 */
HTTP_METHOD_EXCEPTION(11),
/**
 * 验证码错误
 */
VERIFICATION_CODE_EXCEPTION(12),
/**
 * 服务未启动
 */
SERVICE_NOT_ENABLE(20),

/**
 * 客户端 ID 不正确
 */
CLIENT_ID_EXCEPTION(66),
/**
 * 客户端参数异常
 */
CLIENT_PARAM_EXCEPTION(66),
CLIENT_NO_ENABLE_EXCEPTION(67),


OTHER_LOGIN_STATE_NOT_EXIST(55),
ACCOUNT_NOT_EXIST(60),
/**
 * token 认证异常
 */
OAUTH2_TOKEN_AUTH_EXCEPTION(995),


    /**
     * 消息中心返回的异常
     */
    MESSAGE_GROUP_FREEZE_EXCEPTION(600),
    MESSAGE_GROUP_NULL_POINTER_EXCEPTION(600),
    MESSAGE_UNKNOWN_TYPE_EXCEPTION(600),
    MESSAGE_PART_NULL_POINTER_EXCEPTION(600),
    MESSAGE_PLATFORM_NULL_POINTER_EXCEPTION(600),

    MESSAGE_TEMPLATE_NULL_POINTER_EXCEPTION(600),
    MESSAGE_LIMIT_OVERFLOW_EXCEPTION(600),


    /**
     * IP 不在 IP 池中
 */
IP_NOT_POOL_EXCEPTION(998);



private Integer code;
HttpErrorModel(Integer code) {
    this.code = code;
}

public static HttpErrorModel create(String name) {
        for (HttpErrorModel  error: HttpErrorModel.values()) {
                if (error.name().equals(name)) {
                        return error;
                }
        }
        return HttpErrorModel.SERVICE_EXCEPTION;
}

  public Integer getCode() {
    return code;
}
}
