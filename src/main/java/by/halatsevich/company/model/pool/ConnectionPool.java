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

public enum ConnectionPool {

    INSTANCE;

    private final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private int poolSize;
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenConnections;

    ConnectionPool() {
        ConnectionConfig config = new ConnectionConfig();
        try {
            Class.forName(config.getDriverName());
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Cannot register driver");
            throw new RuntimeException("Cannot register driver", e);
        }
        poolSize = config.getPoolSize();
        freeConnections = new LinkedBlockingDeque<>(poolSize);
        givenConnections = new ArrayDeque<>();
        String url = config.getUrl();
        Properties properties = config.getProperties();
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

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Error while taking connection from queue");
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection && givenConnections.remove(connection)) {
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.log(Level.ERROR, "Invalid connection to release");
        }
    }

    public void destroyPool() {
        try {
            for (int i = 0; i < poolSize; i++) {
                freeConnections.take().reallyClose();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error while closing connection");
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Error while taking connection from queue");
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
