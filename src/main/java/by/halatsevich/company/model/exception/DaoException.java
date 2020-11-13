package by.halatsevich.company.model.exception;

/**
 * The class represents dao exception.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class DaoException extends Exception {

    /**
     * Instantiates a new Dao exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
