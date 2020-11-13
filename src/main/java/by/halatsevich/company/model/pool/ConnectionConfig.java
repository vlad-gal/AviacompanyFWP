package by.halatsevich.company.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * The class represents connection config. Loads properties, define pool size and driver name.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
class ConnectionConfig {
    private static final Logger logger = LogManager.getLogger(ConnectionConfig.class);
    private static final ConnectionConfig instance = new ConnectionConfig();
    private static final String PROPERTIES_PATH = "db.properties";
    private static final String DB_URL = "url";
    private static final String DB_POLL_SIZE = "poolSize";
    private static final String DB_DRIVER_NAME = "driverName";
    final Properties properties;
    final String url;
    final int poolSize;
    final String driverName;


    private ConnectionConfig() {
        ClassLoader classLoader = getClass().getClassLoader();
        properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(PROPERTIES_PATH));
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while reading database properties", e);
            throw new RuntimeException("Error while reading database properties", e);
        }
        String sizePoll = properties.getProperty(DB_POLL_SIZE);
        int poolSizeTemp;
        try {
            poolSizeTemp = Integer.parseInt(sizePoll);
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Error while parsing pool size", e);
            poolSizeTemp = 8;
        }
        poolSize = poolSizeTemp;
        url = properties.getProperty(DB_URL);
        driverName = properties.getProperty(DB_DRIVER_NAME);
    }

    /**
     * Gets connection config instance.
     *
     * @return the instance
     */
    public static ConnectionConfig getInstance() {
        return instance;
    }
}
