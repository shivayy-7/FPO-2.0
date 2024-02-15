package com.fpo.web.services;

import com.aashdit.umt.model.User;

public interface MailService {

	/**
	 * mail otp to the user
	 * @param message
	 * @param subject
	 * @param user
	 */
	boolean sendMailBySubject(String message, String subject, User user);
	 
	boolean generateMailUtil(Long amontProcessed, Long noOfVerifyRecords, Long noOfPAYRecords, String fileName);

	String getMessage(String string, String string2);
	
	Boolean mailPasswordDetails(String message, String subject, User user);
	
}
