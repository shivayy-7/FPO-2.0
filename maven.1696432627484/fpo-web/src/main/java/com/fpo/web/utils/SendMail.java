package com.fpo.web.utils;

import com.aashdit.framework.core.ServiceOutcome;

import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class SendMail {
  private static final Logger logger = Logger.getLogger(SendMail.class);
  
  public static ServiceOutcome<String> sendMail(String body, String subject, String mailTo, String mailFrom, String bodyType) {
    //final ResourceBundle rb = ResourceBundle.getBundle("application");
    ServiceOutcome<String> svc = new ServiceOutcome<>();
//    try {
////      Properties props = new Properties();
//////      props.put("mail.smtp.auth", rb.getString("mail.smtp.auth"));
//////      props.put("mail.smtp.starttls.enable", rb.getString("mail.smtp.starttls.enable"));
//////      props.put("mail.smtp.host", rb.getString("mail.smtp.host"));
//////      props.put("mail.smtp.ssl.trust", rb.getString("mail.smtp.ssl.trust"));
//////      props.put("mail.smtp.port", rb.getString("mail.smtp.port"));
//////      Session session = Session.getInstance(props, new Authenticator() {
//////            protected PasswordAuthentication getPasswordAuthentication() {
//////              return new PasswordAuthentication(rb.getString("mail.username"), rb.getString("mail.password"));
//////            }
//////          });
////      MimeMessage mimeMessage = new MimeMessage(session);
////      mimeMessage.setFrom((Address)new InternetAddress(mailFrom));
////      mimeMessage.setRecipients(Message.RecipientType.TO, (Address[])InternetAddress.parse(mailTo));
////      mimeMessage.setSubject(subject);
////      mimeMessage.setHeader("Date", new Date().toString());
////      if ("TEXT".equalsIgnoreCase(bodyType)) {
////        mimeMessage.setText(body);
////      } else {
////        mimeMessage.setContent(body, "text/html");
////      } 
////      Transport.send((Message)mimeMessage);
////      logger.debug("Email Sent Succussfully");
////      svc.setData("SUCCESS");
////      svc.setOutcome(Boolean.valueOf(true));
////    } catch (MessagingException mex) {
////      logger.error("ERROR : MailServiceUtil -> sendMail " + mex.getMessage());
////      svc.setData(mex.getMessage());
////      svc.setMessage(mex.getMessage());
////      svc.setOutcome(Boolean.valueOf(false));
//    } 
    return svc;
  }
}
