package club.p6e.germ.message.exception;

/**
 * 限流异常
 * @author lidashuang
 * @version 1.0
 */
public class LimitOverflowException extends Exception {

    public LimitOverflowException(String message) {
        super(message);
    }

    public LimitOverflowException(Throwable cause) {
        super(cause);
    }
}
