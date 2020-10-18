package by.halatsevich.company.controller.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private static final String DEFAULT_BUNDLE_NAME = "local";
    private static final String REGEX_HYPHEN = "-";
    private ResourceBundle resourceBundle;

    public MessageManager(String lang) {
        resourceBundle = ResourceBundle.getBundle(DEFAULT_BUNDLE_NAME,parseLang(lang));
    }

    public MessageManager(String bundleName, String lang) {
        resourceBundle = ResourceBundle.getBundle(bundleName,parseLang(lang));
    }

    public String getMessage(String key) {
        return resourceBundle.getString(key);
    }

    private Locale parseLang(String lang) {
        String[] localeParameter = lang.split(REGEX_HYPHEN);
        return new Locale(localeParameter[0], localeParameter[1]);
    }

}
