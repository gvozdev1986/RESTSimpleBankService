package by.htp.hvozdzeu.dao.connection.exception;

import by.htp.hvozdzeu.dao.exception.DAOException;

/**
 * @author Aliaksandr Hvozdzeu
 * @see by.htp.hvozdzeu.dao.exception.DAOException;
 * <p/>
 * <code>ConnectionException</code>
 * <p/>
 * Exception class created specifically to describe the exceptional
 * situation arises in the Connection pool layer application.
 */
public class ConnectionException extends DAOException {

    private static final long serialVersionUID = 5140953181953392722L;

	public ConnectionException() {
    }

    public ConnectionException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(Throwable cause) {
        super(cause);
    }
}