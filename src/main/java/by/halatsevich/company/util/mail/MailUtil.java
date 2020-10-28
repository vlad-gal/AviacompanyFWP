package by.halatsevich.company.util.mail;

import by.halatsevich.company.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class MailUtil {
    private static final Logger logger = LogManager.getLogger(MailUtil.class);
    private static final MailUtil instance = new MailUtil();
    private static final String ACTIVATION_EMAIL_SUBJECT = "local.mail.subjectActivationAccount";
    private static final String RESET_PASSWORD_EMAIL_SUBJECT = "local.mail.subjectResetPassword";
    private static final String ACTIVATION_EMAIL_MESSAGE = "local.mail.activationMessage";
    private static final String RESET_PASSWORD_EMAIL_MESSAGE = "local.mail.resetPasswordMessage";
    private static final String CONFIRM_COMMAND_SUFFIX = "?command=confirm_account&email=";
    private static final String RESET_PASSWORD_COMMAND_SUFFIX = "?command=reset_password_page&email=";
    private static final String REGEX_DELIMITER = "\n";

    private MailUtil() {
    }

    public static MailUtil getInstance() {
        return instance;
    }

    public void sendActivationLink(String lang, String userEmail, String link) {
        ResourceBundle bundle = ResourceBundle.getBundle(MailParameter.PROPERTIES_PATH);
        Properties properties = initializeProperties(bundle);
        MessageManager messageManager = new MessageManager(lang);
        String subject = messageManager.getMessage(ACTIVATION_EMAIL_SUBJECT);
        StringJoiner message = new StringJoiner(REGEX_DELIMITER);
        message.add(messageManager.getMessage(ACTIVATION_EMAIL_MESSAGE));
        message.add(link + CONFIRM_COMMAND_SUFFIX + userEmail);
        MailSender sender = new MailSender(userEmail, subject, message.toString(), properties);
        sender.sent();
        logger.log(Level.DEBUG, "Activation link sent");
    }

    public void sendResetPasswordLink(String lang, String userEmail, String link) {
        ResourceBundle bundle = ResourceBundle.getBundle(MailParameter.PROPERTIES_PATH);
        Properties properties = initializeProperties(bundle);
        MessageManager messageManager = new MessageManager(lang);
        String subject = messageManager.getMessage(RESET_PASSWORD_EMAIL_SUBJECT);
        StringJoiner message = new StringJoiner(RESET_PASSWORD_EMAIL_MESSAGE);
        message.add(messageManager.getMessage(ACTIVATION_EMAIL_MESSAGE));
        message.add(link + RESET_PASSWORD_COMMAND_SUFFIX + userEmail);
        MailSender sender = new MailSender(userEmail, subject, message.toString(), properties);
        sender.sent();
        logger.log(Level.DEBUG, "Reset password link sent");
    }

    private Properties initializeProperties(ResourceBundle bundle) {
        Properties properties = new Properties();
        properties.put(MailParameter.PORT, bundle.getString(MailParameter.PORT));
        properties.put(MailParameter.HOST, bundle.getString(MailParameter.HOST));
        properties.put(MailParameter.AUTH, bundle.getString(MailParameter.AUTH));
        properties.put(MailParameter.NAME, bundle.getString(MailParameter.NAME));
        properties.put(MailParameter.PASSWORD, bundle.getString(MailParameter.PASSWORD));
        properties.put(MailParameter.SOCKET_FACTORY, bundle.getString(MailParameter.SOCKET_FACTORY));
        return properties;
    }
}
