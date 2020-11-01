package by.halatsevich.company.util.mail;

import by.halatsevich.company.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;
import java.util.StringJoiner;

public class MailUtil {
    private static final Logger logger = LogManager.getLogger(MailUtil.class);
    private static final MailUtil instance = new MailUtil();
    private static final String PROPERTIES_PATH = "mail.properties";
    private static final String ACTIVATION_EMAIL_SUBJECT = "local.mail.subjectActivationAccount";
    private static final String RESET_PASSWORD_EMAIL_SUBJECT = "local.mail.subjectResetPassword";
    private static final String ACTIVATION_EMAIL_MESSAGE = "local.mail.activationMessage";
    private static final String RESET_PASSWORD_EMAIL_MESSAGE = "local.mail.resetPasswordMessage";
    private static final String CONFIRM_COMMAND_SUFFIX = "?command=confirm_account&email=";
    private static final String RESET_PASSWORD_COMMAND_SUFFIX = "?command=reset_password_page&email=";
    private static final String REGEX_DELIMITER = "\n";

    private MailUtil() {
    }

    public enum MailType {
        ACTIVATION, RESET
    }

    public static MailUtil getInstance() {
        return instance;
    }

    public void sendMessage(String lang, String userEmail, String link, MailType mailType) {
        MessageManager messageManager = new MessageManager(lang);
        StringJoiner message = new StringJoiner(REGEX_DELIMITER);
        String subject;
        if (mailType == MailType.ACTIVATION) {
            subject = messageManager.getMessage(ACTIVATION_EMAIL_SUBJECT);
            message.add(messageManager.getMessage(ACTIVATION_EMAIL_MESSAGE));
            message.add(link + CONFIRM_COMMAND_SUFFIX + userEmail);
        } else {
            subject = messageManager.getMessage(RESET_PASSWORD_EMAIL_SUBJECT);
            message.add(messageManager.getMessage(RESET_PASSWORD_EMAIL_MESSAGE));
            message.add(link + RESET_PASSWORD_COMMAND_SUFFIX + userEmail);
        }
        MailSender sender = new MailSender(userEmail, subject, message.toString(), initializeProperties());
        sender.sent();
    }

    private Properties initializeProperties() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(PROPERTIES_PATH));
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error while reading mail properties", e);
        }
        return properties;
    }
}
