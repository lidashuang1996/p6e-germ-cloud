package club.p6e.germ.message.exception;

/**
 * 未知异常
 * @author lidashuang
 * @version 1.0
 */
public class UnknownException extends Exception {

    public UnknownException(String message) {
        super(message);
    }

    public UnknownException(Throwable cause) {
        super(cause);
    }
}
