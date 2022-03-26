public class IncorrectCommandException extends Exception {

    IncorrectCommandException() {
        super();
    }

    IncorrectCommandException(String errorMessage) {
        super(errorMessage);
    }

    IncorrectCommandException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    IncorrectCommandException(Throwable cause) {
        super(cause);
    }
}
