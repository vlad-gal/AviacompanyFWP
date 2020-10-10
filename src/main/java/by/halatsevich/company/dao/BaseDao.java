package by.halatsevich.company.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface BaseDao {
    Logger logger = LogManager.getLogger(BaseDao.class);

    default void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while closing statement", e);
        }
    }

    default void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while closing ResultSet", e);
        }
    }
}