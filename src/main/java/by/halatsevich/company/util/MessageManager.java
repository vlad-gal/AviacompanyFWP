package by.halatsevich.company.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private static final String DEFAULT_BUNDLE_NAME = "local";
    private ResourceBundle resourceBundle;

    public MessageManager(String lang) {
        resourceBundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME, new Locale(lang));
    }

    public MessageManager(String bundleName, String lang) {
        resourceBundle = ResourceBundle.getBundle(bundleName, new Locale(lang));
    }

    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }
}
