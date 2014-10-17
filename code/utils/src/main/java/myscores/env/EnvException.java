package myscores.env;

public class EnvException extends RuntimeException {

    public EnvException(String message) {
        super(message);
    }

    public EnvException(String message, Throwable cause) {
        super(message, cause);
    }

    public EnvException(Throwable cause) {
        super(cause);
    }
}
