package com.capeelectric.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capeelectric.exception.SMSException;
import com.capeelectric.service.SMSService;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@RestController
@RequestMapping("/api/v1")
public class SMSController {
	
	private static final Logger logger = LoggerFactory.getLogger(SMSController.class);

	@Autowired
	private SMSService smsService;

	@GetMapping("/sendOtp/{mobileNumber}")
	public ResponseEntity<String> sendOtp(@PathVariable String mobileNumber) throws UnirestException, SMSException {
		
		logger.info("Started sendOtp function userName: {}", mobileNumber);

		String responseMessage = smsService.sendOtp(mobileNumber);
		
		logger.info("Ended sendOtp function in SMScontroller");

		return new ResponseEntity<String>(responseMessage, HttpStatus.ACCEPTED);
	}

	@GetMapping("/verifyOtp/{sessionId}/{otp}")
	public ResponseEntity<String> verifyOtp(@PathVariable String sessionId, @PathVariable String otp)
			throws UnirestException, SMSException {
		
		logger.info("Started verifyOtp function userName: {}, OTP: {}", sessionId, otp);

		String responseMessage = smsService.verifyOtp(sessionId, otp);
		
		logger.info("Ended verifyOtp function in SMScontroller");

		return new ResponseEntity<String>(responseMessage, HttpStatus.ACCEPTED);
	}
}







