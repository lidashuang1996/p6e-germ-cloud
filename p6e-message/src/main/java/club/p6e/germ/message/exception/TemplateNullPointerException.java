package club.p6e.germ.message.exception;

/**
 * 模版空异常
 * @author lidashuang
 * @version 1.0
 */
public class TemplateNullPointerException extends Exception {

    public TemplateNullPointerException(String message) {
        super(message);
    }

    public TemplateNullPointerException(Throwable cause) {
        super(cause);
    }
}
