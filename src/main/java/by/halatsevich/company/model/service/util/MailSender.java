//package by.halatsevich.company.model.service.util;
//
//public class MailSender {
//    private MimeMessage message;
//    private String sendToEmail;
//    private String mailSubject;
//    private String mailText;
//    private Properties properties;
//    public MailSender(String sendToEmail, String mailSubject, String mailText,
//                      Properties properties) {
//        this.sendToEmail = sendToEmail;
//        this.mailSubject = mailSubject;
//        this.mailText = mailText;
//        this.properties = properties;
//    }
//    public void send() {
//        try {
//            initMessage();
//            Transport.send(message); // sending mail
//        } catch (AddressException e) {
//            System.err.println("Invalid address: " + sendToEmail + "  " + e); // in log
//        } catch (MessagingException e) {
//            System.err.println("Error generating or sending message: " + e); // in log
//        }
//    }
//    private void initMessage() throws MessagingException {
//        // mail session object
//        Session mailSession = SessionFactory.createSession(properties);
//        mailSession.setDebug(true);
//        message = new MimeMessage(mailSession); // create a mailing object
//        // loading parameters into the mail message object
//        message.setSubject(mailSubject);
//        message.setContent(mailText, "text/html");
//        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
//    }
//// # 13 # создание почтовой сессии # SessionFactory.java */
//    public static Session createSession(Properties configProperties) {
//        String userName = configProperties.getProperty("mail.user.name");
//        String userPassword = configProperties.getProperty("mail.user.password");
//        return Session.getDefaultInstance(configProperties,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(userName, userPassword);
//                    }
//                });
//    }
//}