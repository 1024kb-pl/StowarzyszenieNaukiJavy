package com.mac.bry.simpleTodo.utilitis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class EmailSender {
	
	
	
	public static void configureEmail(String reciver, String newPassword) {
		
		Properties properties = PropertiesFileLoader.loadPropertiesFile("mail.properties");
		
		final String fromEmail = properties.getProperty("sender"); //requires valid gmail id
		final String password = properties.getProperty("password"); // correct password for gmail id
		String toEmail = reciver; // can be any email id 
		
		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", properties.getProperty("smtp.host")); //SMTP Host
		props.put("mail.smtp.socketFactory.port", properties.get("SSL.port")); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", properties.get("SMTP.port")); //SMTP Port
		
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
	        EmailUtil.sendEmail(session, toEmail,"Password Reset - SIMPLETODO APP", "Your new Password is: " + newPassword);
	}
}
