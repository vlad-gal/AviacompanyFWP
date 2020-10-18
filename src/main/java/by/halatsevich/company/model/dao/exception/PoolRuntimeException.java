package by.halatsevich.company.model.dao.exception;

public class PoolRuntimeException extends RuntimeException {
    public PoolRuntimeException() {
        super();
    }

    public PoolRuntimeException(String message) {
        super(message);
    }

    public PoolRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoolRuntimeException(Throwable cause) {
        super(cause);
    }
}
