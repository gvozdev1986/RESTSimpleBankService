package by.htp.hvozdzeu.service.exception;

/**
 * @author Aliaksandr Hvozdzeu
 * @see java.lang.Exception;
 *         <code>ServiceException</code>
 *         <p/>
 *         Exception class created specifically to describe the exceptional
 *         situation arises in the service layer application.
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -8444530990199529289L;

	public ServiceException() {
        super();
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
