package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.entity.Entity;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface BaseDao<E extends Entity> {
    Logger logger = LogManager.getLogger(BaseDao.class);

    List<E> findAll() throws DaoException;

    List<E> findAllByStatus(Status status) throws DaoException;

    Optional<E> findById(int id) throws DaoException;

    boolean update(E entity) throws DaoException;

    boolean remove(int id) throws DaoException;

    default void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while closing statement", e);
        }
    }

    default void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while closing connection", e);
        }
    }

    default void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while rollback transaction", e);
        }
    }

    default void returnAutoCommit(Connection connection) {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while return auto commit transaction", e);
        }
    }
}