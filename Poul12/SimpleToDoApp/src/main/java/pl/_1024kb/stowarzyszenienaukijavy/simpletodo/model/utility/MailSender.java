package pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.utility;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailSender
{
    public void sendEmail(String email)
    {
        String host = "smtp.gmail.com";
        String username = "example@gmail.com";
        String password = "example";
        Properties properties = System.getProperties();
        properties.put("mail.smtps.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.socketFactory.port", "587");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Authenticator authenticator = new Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        };

        Session session = Session.getDefaultInstance(properties, authenticator);

        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email, "NoReply-JD"));
            message.setReplyTo(InternetAddress.parse(email, false));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
            message.setSubject("Email title");
            message.setText("This email message");

            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }
}
