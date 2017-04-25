package com.smtp.EmailSMTP;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	final String username = "smtpfakemail93@gmail.com";
        final String password = "1q2w3e4r!";
        String msgToSent = "This is my first mail using SMTP";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        Session session = Session.getInstance(properties, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            };
        });
       
        Message message = new MimeMessage(session);
       
            try {
				message.setFrom(new InternetAddress(username));
			
           
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username));
				message.setSubject("Testing");
		        message.setText(msgToSent);
		        Transport.send(message);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
            
       
        System.out.println("Following email was sent: " + "'" + msgToSent + "'");
    }   
    
}
