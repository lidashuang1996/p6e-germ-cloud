package club.p6e.germ.cloud.console.infrastructure.error;

/**
 * @author lidashuang
 * @version 1.0
 */
public class P6eResourceNoExistException extends RuntimeException {

    public P6eResourceNoExistException() {
    }

    public P6eResourceNoExistException(String message) {
        super(message);
    }
}
