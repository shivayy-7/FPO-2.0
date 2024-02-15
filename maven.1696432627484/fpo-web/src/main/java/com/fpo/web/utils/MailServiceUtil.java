package com.fpo.web.utils;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MailServiceUtil {
	


	private static final Logger logger = Logger.getLogger(MailServiceUtil.class);
	
//	@Autowired
//	private  YamlAppProperties yaml;
	
//	private static Boolean auth =yaml.getAuth();
//	
//	private static Boolean enb =yaml.getEnable();
//	
//	private static String host =yaml.getHost();
//	
//	private static String trust =yaml.getTrust();
//	
//	private static String port =yaml.getPort();
//	
//	private static String user =yaml.getUsername();
//	
//	private static String protocols =yaml.getProtocol();
	
	public static  String sendMail(String body, String subject, String mailTo,final String mailFrom, String bodyType) { 		

		Properties props;
//		ResourceBundle rb = ResourceBundle.getBundle("application");
		String exceptionMessage = "";
		
	
		try {
			props = new Properties();

			props.put("mail.smtp.auth", true );
			props.put("mail.smtp.starttls.enable",true);
			props.put("mail.smtp.host","smtp.gmail.com");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port","587");
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");
			
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("aashdittechnologies6@gmail.com", "Aashdit@1234$");
				}
			});
			
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(mailFrom));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
			
			message.setSubject(subject);
			if ("TEXT".equalsIgnoreCase(bodyType))
			{
				message.setText(body);
			}
			else
			{
				message.setContent(body, "text/html");
			}
		

			Transport.send(message);
			logger.debug("Email Sent Succussfully");

			return "SUCCESS";
			
		} catch (MessagingException mex) {
			mex.printStackTrace();
			logger.error(mex.getMessage());
			exceptionMessage = mex.getMessage();
		}
		return exceptionMessage;

	}

}
