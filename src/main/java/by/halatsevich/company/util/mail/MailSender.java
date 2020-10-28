package by.halatsevich.company.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

class MailSender {
    private static final Logger logger = LogManager.getLogger(MailSender.class);
    private static final String MIME_TYPE_TEXT_HTML = "text/plain; charset=utf-8";
    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    MailSender(String sendToEmail, String mailSubject, String mailText, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

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
        String userName = configProperties.getProperty(MailParameter.NAME);
        String userPassword = configProperties.getProperty(MailParameter.PASSWORD);
        return Session.getDefaultInstance(configProperties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}