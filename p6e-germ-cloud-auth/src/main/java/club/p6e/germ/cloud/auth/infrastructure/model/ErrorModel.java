package club.p6e.germ.cloud.auth.infrastructure.model;

/**
 * @author lidashuang
 * @version 1.0
 */
public enum ErrorModel {

    /** 请求方法异常 */
    HTTP_METHOD_EXCEPTION(405),
    /** 服务异常 */
    SERVICE_EXCEPTION(500),
    /** DB 操作异常 */
    DATABASE_EXCEPTION(510),
    /** 登录-账号密码 */
    VOUCHER_EXPIRE_EXCEPTION(600),
    ACCOUNT_OR_PASSWORD_EXCEPTION(610),
    ACCOUNT_NOT_EXIST_EXCEPTION(620),
    ACCOUNT_EXIST_EXCEPTION(630),
    /** 登录-验证码错误 */
    VERIFICATION_CODE_EXCEPTION(640),
    OTHER_LOGIN_STATE_NOT_EXIST(650),
    TOKEN_EXPIRE_EXCEPTION(660),
    QR_CODE_EXPIRE_EXCEPTION(670),
    RAS_EXPIRE_EXCEPTION(680),
    /** 资源不存在 */
    RESOURCES_NO_EXIST_EXCEPTION(700),
    /** 资源存在关联数据 relation */
    RESOURCES_EXIST_RELATION_EXCEPTION(710),
    /** 服务未启动 */
    SERVICE_NOT_ENABLE(900),
    /** 参数异常 */
    PARAMETER_EXCEPTION(1000),
    /** 客户端 */
    CLIENT_ID_PARAMETER_EXCEPTION(1110),
    CLIENT_REDIRECT_URI_PARAMETER_EXCEPTION(1120),
    CLIENT_RESPONSE_TYPE_EXCEPTION(1130),
    CLIENT_SCOPE_PARAMETER_EXCEPTION(1140),
    CLIENT_AUTH_CODE_NOT_EXIST_EXCEPTION(1150),
    CLIENT_ACCOUNT_OR_PASSWORD_EXCEPTION(1160);


    private final Integer code;
    ErrorModel(Integer code) {
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }
}
