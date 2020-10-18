package by.halatsevich.company.model.dao.exception;

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
