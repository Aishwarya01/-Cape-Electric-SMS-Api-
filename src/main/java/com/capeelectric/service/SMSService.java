package com.capeelectric.service;

import com.capeelectric.exception.SMSException;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * 
 * @author capeelectricsoftware
 *
 */
public interface SMSService {

	public String sendOtp(String mobileNumber) throws UnirestException, SMSException;

	public String verifyOtp(String sessionId, String otp) throws UnirestException, SMSException;
}
