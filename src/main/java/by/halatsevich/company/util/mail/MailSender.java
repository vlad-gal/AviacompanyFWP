package by.halatsevich.company.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * The class represents mail sender for initialization message and sending email.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
class MailSender {
    private static final Logger logger = LogManager.getLogger(MailSender.class);
    private static final String MIME_TYPE_TEXT_HTML = "text/plain; charset=utf-8";
    private static final String NAME = "mail.user.name";
    private static final String PASSWORD = "mail.user.password";
    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    /**
     * Instantiates a new Mail sender.
     *
     * @param sendToEmail the send to email
     * @param mailSubject the mail subject
     * @param mailText    the mail text
     * @param properties  the properties
     */
    MailSender(String sendToEmail, String mailSubject, String mailText, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    /**
     * Initialization message and sending email.
     */
    void sent() {
        try {
            initMessage();
            Transport.send(message);
            logger.log(Level.INFO, "Message has been successfully sent");
        } catch (AddressException e) {
            logger.log(Level.ERROR, "Invalid address: {}", sendToEmail, e);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, "Error generating or sending message", e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = createSession(properties);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, MIME_TYPE_TEXT_HTML);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }

    private Session createSession(Properties configProperties) {
        String userName = configProperties.getProperty(NAME);
        String userPassword = configProperties.getProperty(PASSWORD);
        return Session.getDefaultInstance(configProperties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}