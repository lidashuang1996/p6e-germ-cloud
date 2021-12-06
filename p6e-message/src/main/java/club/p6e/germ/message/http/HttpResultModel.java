package club.p6e.germ.message.http;

import java.io.Serializable;

/**
 * @author lidashuang
 * @version 1.0
 */
public final class HttpResultModel implements Serializable {

    /**
     * 返回数据的消息状态码
     */
    private Integer code;

    /**
     * 返回数据的消息内容
     *
     * message 不推荐为中文，后期如果需要支持多语言操作很麻烦
     */
    private String message;

    /**
     * 返回数据的对象
     */
    private Object data;

    public static HttpResultModel build() {
        return new HttpResultModel();
    }

    public static HttpResultModel build(HttpErrorModel error) {
        return new HttpResultModel(error);
    }

    public static HttpResultModel build(Object data) {
        return new HttpResultModel(data);
    }

    private HttpResultModel() {
        this.code = 0;
        this.message = "SUCCESS";
    }

    private HttpResultModel(HttpErrorModel error) {
        this.code = error.getCode();
        this.message = error.name();
    }

    private HttpResultModel(Object data) {
        this.code = 0;
        this.message = "SUCCESS";
        this.data = data;
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
