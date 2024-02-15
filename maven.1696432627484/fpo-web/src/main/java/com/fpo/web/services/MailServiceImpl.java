package com.fpo.web.services;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aashdit.umt.model.User;
import com.fpo.web.entities.MailQueued;
import com.fpo.web.entities.MailTemplatesMaster;
import com.fpo.web.repositories.MailQueuedRepository;
import com.fpo.web.repositories.MailRecipantsRepository;
import com.fpo.web.repositories.MailTemplateRepository;

@Service
public class MailServiceImpl implements MailService {

	private static final Logger logger = Logger.getLogger(MailServiceImpl.class);
	//ResourceBundle rb = ResourceBundle.getBundle("application");
	
	@Autowired
	private MailQueuedRepository mailQueuedRepository;
	
	@Autowired
	private MailRecipantsRepository mailRecepantRepository;
	
	@Autowired
	private MailTemplateRepository mailTemplateRepository;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	//@Value("${mail.username}")
	private String MAIL_FROM;
	
	/**
	 *send otp to the user
	 */
	
	@Override
	public boolean sendMailBySubject(String message, String subject, User user) {
		try {
			String emailId = MAIL_FROM;
			Template template = velocityEngine.getTemplate("./template/otpMailTemplate.vm");
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("date", new Date().toString());
			velocityContext.put("userName", user.getFirstName());
			velocityContext.put("message", message+" "+user.getUserName()+" & "+"123456" );
			StringWriter stringWriter = new StringWriter();
			template.merge(velocityContext, stringWriter);
			String body = stringWriter.toString();
			MailQueued mObj = new MailQueued();
			mObj.setMailFrom(emailId);
			mObj.setMailTo(user.getEmail());
			mObj.setSubject(subject);
			mObj.setBody(body);
			mObj.setBodyType("HTML");
			mObj.setStatus("PROCESSING");
			mObj = mailQueuedRepository.save(mObj);
			if (mObj != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return false;
		}
	}

	
	/**
	 * generate payment file details mail template
	 */
	@Override
    public boolean generateMailUtil(Long amontProcessed, Long noOfVerifyRecords, Long noOfPAYRecords,
			String fileName) {
		boolean retval = false;
		try {
			Template template = velocityEngine.getTemplate("./template/defaultTemplate.vm");
			VelocityContext velocityContext = new VelocityContext();

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
			String strDate = formatter.format(new Date());

			velocityContext.put("date", strDate);
			velocityContext.put("fileName", fileName);
			velocityContext.put("totalVerificationProcessed", noOfVerifyRecords);
			velocityContext.put("totalFTOProcessed", noOfPAYRecords);
			velocityContext.put("totalAmountProcessed", amontProcessed);

			StringWriter stringWriter = new StringWriter();
			template.merge(velocityContext, stringWriter);
			String body = stringWriter.toString();
			MailQueued mObj = new MailQueued();
			mObj.setMailFrom("");//MAIL_FROM
			mObj.setMailTo(mailRecepantRepository.findAll().stream().filter(mail -> mail.getType().equals("TO"))
					.findFirst().get().getMail());
			mObj.setBody(body);
			mObj.setSubject("");//MAIL_SUBJECT
			mObj.setStatus("QUEUED");
			mObj.setBodyType("html");
			mObj.setCreatedOn(new Date());
			mailQueuedRepository.save(mObj);
			retval = true;
		} catch (Exception ex) {
			logger.error("ERROR - MailServiceImpl -> generateInvitationMail : " + ex.getMessage());
		}
		return retval;
	}


	@Override
	public String getMessage(String templateType, String templateCode) {
		String message="";
		try {
			MailTemplatesMaster	geTemplate = mailTemplateRepository.findByTemplateTypeAndTemplateCode(templateType, templateCode);
			if(Optional.ofNullable(geTemplate).isPresent()) {
				message = geTemplate.getTemplateName();
			}
			
		} catch (Exception e) {
			logger.error("ERROR - MailServiceImpl -> generateInvitationMail : " + e.getMessage());
		}
		return message;
	}
	
	@Override
	@Transactional
	public Boolean mailPasswordDetails(String message, String subject, User user) {
		try {
//			String emailId = rb.getString("mail.username");
			Template template = velocityEngine.getTemplate("./template/passwordResetTemplate.vm");
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("userName", user.getFirstName()+" "+user.getLastName());
			velocityContext.put("message", message);
//			velocityContext.put("url", rb.getString("pswd.login.url"));
			StringWriter stringWriter = new StringWriter();
			template.merge(velocityContext, stringWriter);
			String body = stringWriter.toString();
			MailQueued mObj = new MailQueued();
			mObj.setMailFrom("aashdittechnologies6@gmail.com");
			mObj.setMailTo(user.getEmail());
			mObj.setSubject(subject);
			mObj.setBody(body);
			mObj.setBodyType("HTML");
			mObj.setStatus("QUEUED");
			mObj = mailQueuedRepository.save(mObj);
			if (mObj != null) {
				return true;
			} else {
				logger.error("Mail Object is null in mailPasswordDetails method in MailServiceImpl.......");
				return false;
			}
		} catch (Exception ex) {
			logger.error("Exception occured in mailPasswordDetails method in MailServiceImpl-->"+ex.getMessage());
			return false;
		}
	}
	
}
