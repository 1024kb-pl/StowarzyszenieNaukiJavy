package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender
{
    private final static String USERNAME = "simpletodo@pocz.pl";
    private final static String PASSWORD = DataReader.read();
    private static final Logger logger = LoggerFactory.getLogger(MailSender.class);

    public static void sendEmail(String receiverAddress, String newPassword) throws MessagingException
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "t.pl");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "t.pl");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverAddress));
            message.setSubject("Reset Password");
            message.setText("You requested for a password reset.\n\nYour new password is: " + newPassword);

            Transport.send(message);

            System.out.println("Mail sent");
            logger.info("Mail sent to {}", receiverAddress);

        } catch (MessagingException e)
        {
            logger.error(e.getMessage());
            throw new MessagingException(e.getMessage());
        }
    }
}
