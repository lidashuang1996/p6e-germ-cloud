package club.p6e.germ.message.exception;

/**
 * 零件异常
 * @author lidashuang
 * @version 1.0
 */
public class PartNullPointerException extends Exception {

    public PartNullPointerException(String message) {
        super(message);
    }

    public PartNullPointerException(Throwable cause) {
        super(cause);
    }
}
