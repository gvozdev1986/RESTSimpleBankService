package by.htp.hvozdzeu.exception;

public class ErrorGetResponseException extends RuntimeException {

    public static final String ERROR_MESSAGE_GET_RESPONSE = "Error getting response from server.";

    private static final long serialVersionUID = -2606978547668796167L;

    public ErrorGetResponseException() {
        super();
    }

    public ErrorGetResponseException(String message) {
        super(message);
    }

    public ErrorGetResponseException(String message, Throwable cause) {
        super(ERROR_MESSAGE_GET_RESPONSE, cause);
    }

    public ErrorGetResponseException(Throwable cause) {
        super(cause);
    }

    protected ErrorGetResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
