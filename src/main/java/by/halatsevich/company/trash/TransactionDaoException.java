package by.halatsevich.company.trash;

import by.halatsevich.company.model.exception.DaoException;

public class TransactionDaoException extends DaoException {
    public TransactionDaoException() {
    }

    public TransactionDaoException(String message) {
        super(message);
    }

    public TransactionDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionDaoException(Throwable cause) {
        super(cause);
    }
}
