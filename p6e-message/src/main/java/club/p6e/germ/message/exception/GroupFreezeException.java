package club.p6e.germ.message.exception;

/**
 * 组冻结异常
 * @author lidashuang
 * @version 1.0
 */
public class GroupFreezeException extends Exception {

    public GroupFreezeException(String message) {
        super(message);
    }

    public GroupFreezeException(Throwable cause) {
        super(cause);
    }
}