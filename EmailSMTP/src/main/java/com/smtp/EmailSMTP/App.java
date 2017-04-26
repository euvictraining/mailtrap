package com.smtp.EmailSMTP;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Scanner;
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
    	
    	String tmp; //variable needed to get user data from text file
    	Scanner in;		
		in = new Scanner(new File("properties.txt"));		
    	tmp = in.nextLine(); 				
		final String username = tmp.substring(10); //get all characters after "smtp.user="
		tmp = in.nextLine();	
		final String password = tmp.substring(14); //get all characters after "smtp.password="
        final String msgToSend = "This is my first mail using SMTP";
        
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
