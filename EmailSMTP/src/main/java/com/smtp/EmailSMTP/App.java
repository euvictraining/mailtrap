package com.smtp.EmailSMTP;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

import org.apache.log4j.Logger;


public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
    	
    	final String msgToSend = "This is my first mail using SMTP";
    	final Properties prop = new Properties();
    	InputStream input = null;

		// load a properties file
    	input = new FileInputStream("properties");    		
    	try {
			prop.load(input);
		} catch (IOException e1) {			
			e1.printStackTrace();
		} 
    	// get the username value
    	final String username = (prop.getProperty("smtp.user"));
    	
    	
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", prop.getProperty("mail.smtp.auth"));
        properties.put("mail.smtp.starttls.enable", prop.getProperty("mail.smtp.starttls.enable"));
        properties.put("mail.smtp.host", prop.getProperty("mail.smtp.host"));
        properties.put("mail.smtp.port", prop.getProperty("mail.smtp.port"));
        
        Session session = Session.getInstance(properties, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, prop.getProperty("smtp.password"));
            };
        });
       
        Message message = new MimeMessage(session);
      
        try {
				message.setFrom(new InternetAddress(username));	   //who sends the email        
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username)); //who gets the email
				message.setSubject("Testing"); //set Title of the message
		        message.setText(msgToSend);  // set content of the email
		        Transport.send(message);
			} catch (AddressException e) {				
				e.printStackTrace();
			} catch (MessagingException e) {				
				e.printStackTrace();
			}
       Logger logger = Logger.getLogger(App.class);
       logger.info("Following email was sent: " + "'" + msgToSend + "'");
       
    }   
    
}
