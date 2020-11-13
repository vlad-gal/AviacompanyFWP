package by.halatsevich.company.model.exception;

/**
 * The class represents service exception.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class ServiceException extends Exception {

    /**
     * Instantiates a new Service exception.
     *
     * @param message the message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Service exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
