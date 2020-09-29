package by.halatsevich.company.dao.pool;

import com.mysql.cj.jdbc.Driver;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static Logger logger = LogManager.getLogger();
    private int poolSize;
    private static ConnectionPool instance = new ConnectionPool();
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenConnections;

    private ConnectionPool() {
        initialization();
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    private void initialization() {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            logger.log(Level.FATAL, "Cannot register driver");
            throw new RuntimeException("Cannot register driver", e);
        }

        ResourceBundle bundle = ResourceBundle.getBundle(PoolParameters.PROPERTIES_PATH);
        String url = bundle.getString(PoolParameters.URL);
        String user = bundle.getString(PoolParameters.USER);
        String password = bundle.getString(PoolParameters.PASSWORD);
        String serverTimezone = bundle.getString(PoolParameters.TIMEZONE);
        String autoReconnect = bundle.getString(PoolParameters.AUTO_RECONNECT);
        String encoding = bundle.getString(PoolParameters.ENCODING);
        String useUnicode = bundle.getString(PoolParameters.UNICODE);
        String sizePoll = bundle.getString(PoolParameters.POLL_SIZE);
        try {
            poolSize = Integer.parseInt(sizePoll);
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Error while parsing pool size", e);
            poolSize = 8;
        }

        freeConnections = new LinkedBlockingDeque<>(poolSize);
        givenConnections = new ArrayDeque<>();
        Properties properties = new Properties();
        properties.put(PoolParameters.USER, user);
        properties.put(PoolParameters.PASSWORD, password);
        properties.put(PoolParameters.TIMEZONE, serverTimezone);
        properties.put(PoolParameters.AUTO_RECONNECT, autoReconnect);
        properties.put(PoolParameters.ENCODING, encoding);
        properties.put(PoolParameters.UNICODE, useUnicode);

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

    private void fillingConnections(String url, Properties properties, int count) {
        for (int i = 0; i < count; i++) {
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
