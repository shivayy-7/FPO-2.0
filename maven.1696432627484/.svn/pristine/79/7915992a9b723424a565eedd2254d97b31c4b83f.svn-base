package com.fpo.web.utils;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class RandomNumberGenerator {

	public static final String CHAR_LIST_TWO = "12345678910";
	 
	public static String otpGenertor()
	{
		SecureRandom random= new SecureRandom();
		int num=random.nextInt(100000);
		String formatted=String.format("%06d", num);
		return formatted; 
	}

	public static int getRandomNumber() {
		int randomInt = 0;
		Random randomNumber = new Random();
		randomInt = randomNumber.nextInt(CHAR_LIST_TWO.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}
	
	public static String generateTrainingCode() {
		StringBuffer buffer = new StringBuffer();
		SecureRandom random= new SecureRandom();
		String randomNumber = String.format("%04d", random.nextInt(10000)); 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String numbers[] = dtf.format(now).split("\\s+");
		String number1[] = numbers[0].split("/");
		String generateFileNoSuffix = number1[0] + number1[1] + number1[2];
		String trainingCode = buffer + generateFileNoSuffix + "/" + randomNumber; 
		return trainingCode;
	}
	
	
}
