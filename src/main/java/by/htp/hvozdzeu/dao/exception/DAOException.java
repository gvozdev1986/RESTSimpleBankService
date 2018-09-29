package by.htp.hvozdzeu.dao.exception;

import by.htp.hvozdzeu.service.exception.ServiceException;

/**
 * @author Aliaksandr Hvozdzeu
 * @see by.htp.hvozdzeu.service.exception.ServiceException;
 *         <code>DAOException</code>
 *         <p/>
 *         Exception class created specifically to describe the exceptional
 *         situation arises in the DAO layer application.
 */
public class DAOException extends ServiceException {

    private static final long serialVersionUID = 1878492385282757535L;

    protected DAOException() {
        super();
    }

    public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

}
