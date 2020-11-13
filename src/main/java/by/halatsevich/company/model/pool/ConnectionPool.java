package by.halatsevich.company.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The class represents connection poll.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public enum ConnectionPool {

    /**
     * Instance connection pool.
     */
    INSTANCE;

    private final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private int poolSize;
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenConnections;

    ConnectionPool() {
        ConnectionConfig config = ConnectionConfig.getInstance();
        try {
            Class.forName(config.driverName);
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Cannot register driver");
            throw new RuntimeException("Cannot register driver", e);
        }
        poolSize = config.poolSize;
        freeConnections = new LinkedBlockingDeque<>(poolSize);
        givenConnections = new ArrayDeque<>();
        String url = config.url;
        Properties properties = config.properties;
        fillingConnections(url, properties, poolSize);
        if (freeConnections.size() < poolSize) {
            int difference = poolSize - freeConnections.size();
            fillingConnections(url, properties, difference);
        }
        if (freeConnections.isEmpty()) {
            logger.log(Level.FATAL, "Cannot create connections");
            throw new RuntimeException("Cannot create connections");
        }
    }

    private void fillingConnections(String url, Properties properties, int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection connection = DriverManager.getConnection(url, properties);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                freeConnections.add(proxyConnection);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Cannot add connection to pool", e);
            }
        }
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Error while taking connection from queue", e);
        }
        return connection;
    }

    /**
     * Release connection.
     *
     * @param connection the connection
     */
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection && givenConnections.remove(connection)) {
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.log(Level.ERROR, "Invalid connection to release");
        }
    }

    /**
     * Destroy pool.
     */
    public void destroyPool() {
        try {
            for (int i = 0; i < poolSize; i++) {
                freeConnections.take().reallyClose();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while closing connection", e);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Error while taking connection from queue", e);
        } finally {
            deregisterDrivers();
        }
    }

    private void deregisterDrivers() {
        while (DriverManager.getDrivers().hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Cannot deregister driver", e);
            }
        }
    }
}
