package club.p6e.germ.message.exception;

/**
 * 未知类型异常
 * @author lidashuang
 * @version 1.0
 */
public class UnknownTypeException extends Exception {

    public UnknownTypeException(String message) {
        super(message);
    }

    public UnknownTypeException(Throwable cause) {
        super(cause);
    }
}
