package by.halatsevich.company.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

class ConnectionConfig {
    private final Logger logger = LogManager.getLogger(ConnectionConfig.class);
    private static final String PROPERTIES_PATH = "db.properties";
    private static final String DB_URL = "url";
    private static final String DB_POLL_SIZE = "poolSize";
    private static final String DB_DRIVER_NAME = "driverName";
    private Properties properties;
    private String url;
    private int poolSize;
    private String driverName;


    ConnectionConfig() {
        ClassLoader classLoader = getClass().getClassLoader();
        properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(PROPERTIES_PATH));
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while reading database properties", e);
            throw new RuntimeException("Error while reading database properties", e);
        }
        String sizePoll = properties.getProperty(DB_POLL_SIZE);
        try {
            poolSize = Integer.parseInt(sizePoll);
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Error while parsing pool size", e);
            poolSize = 8;
        }
        url = properties.getProperty(DB_URL);
        driverName = properties.getProperty(DB_DRIVER_NAME);
    }

    public Properties getProperties() {
        return properties;
    }

    public String getUrl() {
        return url;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public String getDriverName() {
        return driverName;
    }
}
