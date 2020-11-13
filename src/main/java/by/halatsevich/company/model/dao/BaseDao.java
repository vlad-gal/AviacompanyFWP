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

/**
 * The interface represents base dao.
 *
 * @param <E> the type parameter
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface BaseDao<E extends Entity> {
    Logger logger = LogManager.getLogger(BaseDao.class);

    /**
     * Find all entities list.
     *
     * @return the entities list
     * @throws DaoException the dao exception
     */
    List<E> findAll() throws DaoException;

    /**
     * Find all entities by status.
     *
     * @param status the status
     * @return the entities list
     * @throws DaoException the dao exception
     */
    List<E> findAllByStatus(Status status) throws DaoException;

    /**
     * Find entity by id.
     *
     * @param id the entity's id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<E> findById(int id) throws DaoException;

    /**
     * Update boolean.
     *
     * @param entity the updating entity
     * @return true if updating successful, otherwise false
     * @throws DaoException the dao exception
     */
    boolean update(E entity) throws DaoException;

    /**
     * Remove entity by id.
     *
     * @param id the entity's id
     * @return true if removing successful, otherwise false
     * @throws DaoException the dao exception
     */
    boolean remove(int id) throws DaoException;

    /**
     * Close statement.
     *
     * @param statement the statement
     */
    default void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while closing statement", e);
        }
    }

    /**
     * Close connection.
     *
     * @param connection the connection
     */
    default void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while closing connection", e);
        }
    }

    /**
     * Rollback connection.
     *
     * @param connection the connection
     */
    default void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while rollback transaction", e);
        }
    }

    /**
     * Return auto commit.
     *
     * @param connection the connection
     */
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