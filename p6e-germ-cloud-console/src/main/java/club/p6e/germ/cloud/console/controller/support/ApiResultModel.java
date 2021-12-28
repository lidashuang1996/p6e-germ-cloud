package club.p6e.germ.cloud.console.controller.support;

import club.p6e.germ.cloud.console.infrastructure.model.ErrorModel;
import com.p6e.germ.common.utils.P6eCopyUtil;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
public class ApiResultModel implements Serializable {

    /** 返回数据的消息状态码 */
    private Integer code;
    /** 返回数据的消息内容, message 不推荐为中文，后期如果需要支持多语言操作很麻烦 */
    private String message;
    /** 返回数据的对象 */
    private Object data;

    /** 默认的成功消息的内容 */
    private static final String DEFAULT_SUCCESS_MESSAGE_CONTENT = "SUCCESS";

    /**
     * 无参编译
     * @return ApiResultModel 对象
     */
    public static ApiResultModel build() {
        return new ApiResultModel();
    }

    /**
     * 结果编译
     * @param data 结果对象
     * @return ApiResultModel 对象
     */
    public static ApiResultModel build(Object data) {
        return new ApiResultModel(data);
    }

    /**
     * 结果转换编译
     * @param data 结果对象
     * @return ApiResultModel 对象
     */
    public static ApiResultModel build(Object data, Class<?> clazz) {
        return new ApiResultModel(P6eCopyUtil.run(data, clazz));
    }

    /**
     * 错误编译
     * @param error 错误对象
     * @return ApiResultModel 对象
     */
    public static ApiResultModel build(ErrorModel error) {
        return new ApiResultModel(error);
    }

    private ApiResultModel() {
        this.code = 0;
        this.message = DEFAULT_SUCCESS_MESSAGE_CONTENT;
    }

    private ApiResultModel(ErrorModel error) {
        this.code = error.getCode();
        this.message = error.name();
    }

    private ApiResultModel(Object data) {
        this.code = 0;
        this.data = data;
        this.message = DEFAULT_SUCCESS_MESSAGE_CONTENT;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
