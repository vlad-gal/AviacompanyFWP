package by.halatsevich.company.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The class represents message manager.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class MessageManager {
    private static final String DEFAULT_BUNDLE_NAME = "local";
    private ResourceBundle resourceBundle;

    /**
     * Instantiates a new Message manager.
     *
     * @param lang the lang
     */
    public MessageManager(String lang) {
        resourceBundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, new Locale(lang));
    }

    /**
     * Instantiates a new Message manager.
     *
     * @param bundleName the bundle name
     * @param lang       the lang
     */
    public MessageManager(String bundleName, String lang) {
        resourceBundle = ResourceBundle.getBundle(bundleName, new Locale(lang));
    }

    /**
     * Gets message.
     *
     * @param key the key
     * @return the message
     */
    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }
}
