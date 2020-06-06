package pl._1024kb.stowarzyszenienaukijavy.simpletodo.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailSenderTest
{
    private static final String MAIL_ADDRESS = "poulg12@gmail.com";

    private MailSender mailSender = new MailSender();

    @Test
    public void shouldReturnSuccessMessage() throws MessagingException
    {
        /*
       final String pass = System.getenv("PASS_FOR_EMAIL_SIMPLE_TODO");
       String message = mailSender.sendEmail(MAIL_ADDRESS, pass);
       assertEquals(MailSender.MESSAGE, message);
       */
    }

    @Test(expected = MessagingException.class)
    public void shouldThrowMessagingException() throws MessagingException
    {
        /*
        final String wrongPass = "wrongPass";
        mailSender.sendEmail(MAIL_ADDRESS, wrongPass);
        */
    }
}