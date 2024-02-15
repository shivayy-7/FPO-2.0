package com.fpo.web.scheduler.tasks;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fpo.web.entities.MailQueued;
import com.fpo.web.repositories.MailQueuedRepository;
import com.fpo.web.utils.MailServiceUtil;

@Component()
public class EmailSendTask {

	@Autowired
	private MailQueuedRepository mailRepository;

	private static final Logger logger = Logger.getLogger(EmailSendTask.class);

	@Scheduled(fixedRate = 60000) // Every 1 Min
	private void markForProcessiong() {
		sendEmail();
	}

	private void sendEmail() {

		try {
//			ResourceBundle rb = ResourceBundle.getBundle("messages");
			List<MailQueued> queuedLst = mailRepository.findEmailList("QUEUED");

			if (queuedLst.size() > 0) {

				for (MailQueued item : queuedLst) {
					item.setStatus("PROCESSING");
					mailRepository.save(item);
				}
			}

			// Update the selected items to processing

			String isEmailed = null;
			MailQueued mailObj = null;

			List<MailQueued> processedLst = mailRepository.findEmailList("PROCESSING");

			if (processedLst.size() < 1) {
				return;
			}

			// logger.debug("Hiiiii"+processedLst.size());

			for (MailQueued queued : processedLst) {

				try {

					isEmailed = MailServiceUtil.sendMail(queued.getBody(), queued.getSubject(), queued.getMailTo(),
							queued.getMailFrom(), queued.getBodyType());

					if (isEmailed.equals("SUCCESS")) {
						mailObj = mailRepository.findById(queued.getEntryId()).get();
						queued.setStatus("SENT");
						queued.setUpdatedOn(new Date());
						mailRepository.save(queued);
						logger.debug("Email Sent Succussfully");

					} else {
						// mailObj =
						// mailRepository.findOne(queued.getEntryId());
						queued.setStatus("FAILED");
						queued.setUpdatedOn(new Date());
						queued.setFailureReason(isEmailed);
						mailRepository.save(queued);
						logger.debug("Email Sending Failed");
					}
				} catch (Exception e) {
					queued.setStatus("FAILED");
					queued.setUpdatedOn(new Date());
					queued.setFailureReason(e.getMessage());
					e.printStackTrace();
					mailRepository.save(queued);
					logger.error(e.getMessage());
				}
			}
		} catch (Exception ex) {

			logger.error(ex.getMessage());
		}

	}

}
