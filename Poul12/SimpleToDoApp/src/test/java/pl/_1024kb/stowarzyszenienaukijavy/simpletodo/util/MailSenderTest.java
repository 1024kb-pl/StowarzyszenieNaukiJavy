package pl._1024kb.stowarzyszenienaukijavy.simpletodo.util;

import org.junit.Assert;
import org.junit.Test;

import javax.mail.MessagingException;

import static org.junit.Assert.*;

public class MailSenderTest
{
    private static final String MAIL_ADDRESS = "poulg12@gmail.com";


    @Test
    public void shouldReturnSuccessMessage() throws MessagingException
    {
        String message = MailSender.sendEmail(MAIL_ADDRESS, "newPAss");
        Assert.assertEquals(MailSender.MESSAGE, message);
    }

    @Test(expected = MessagingException.class)
    public void shouldThrowMessagingException() throws MessagingException
    {
        MailSender.sendEmail(MAIL_ADDRESS, "newPass");
    }
}