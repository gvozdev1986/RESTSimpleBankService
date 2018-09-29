package by.htp.hvozdzeu.exception;

public class DataException extends RuntimeException {

    public static final String ERROR_MESSAGE_NO_DATA_ENCODE = "No data to encode.";

    private static final long serialVersionUID = -2606978547668796167L;

    public DataException() {
        super();
    }

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataException(Throwable cause) {
        super(cause);
    }

    protected DataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
