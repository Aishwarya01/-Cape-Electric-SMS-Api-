package com.capeelectric.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.config.SMSConfig;
import com.capeelectric.exception.SMSException;
import com.capeelectric.service.SMSService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Service
public class SMSServiceImpl implements SMSService {

	private static final Logger logger = LoggerFactory.getLogger(SMSServiceImpl.class);

	@Autowired
	private SMSConfig smsProperties;

	private HttpResponse<String> response;

	@Override
	public String sendOtp(String mobileNumber) throws UnirestException, SMSException {

		if (mobileNumber != null && !mobileNumber.isEmpty()) {

			try {

				logger.info("Calling...  2Factor_API Service for Sending OTP");
				response = Unirest
						.get("https://2factor.in/API/V1/" + smsProperties.getApiKey() + "/SMS/" + mobileNumber + "/"
								+ smsProperties.getOtpType() + "/" + smsProperties.getTemplateName())
						.header("content-type", "application/x-www-form-urlencoded").asString();

			} catch (Throwable e) {
				logger.info("Calling...  2Factor_API Service was Faild :" + e.getMessage());
				throw new SMSException("The exception is " + e.getMessage());
			}

		} else {
			logger.info("Given Mobile Number is Invalid : " + mobileNumber);
			throw new SMSException("Invaild inputs");
		}

		logger.info("2Factor_API SendOtp Service Call Ended : " + response.getStatus());
		return response.getBody();

	}

	@Override
	public String verifyOtp(String sessionId, String otp) throws UnirestException, SMSException {

		if (otp != null && !otp.isEmpty() && sessionId != null && !sessionId.isEmpty()) {

			try {
				logger.info("Calling...  2Factor_API Verifying OTP Service");

				response = Unirest.get("https://2factor.in/API/V1/" + smsProperties.getApiKey() + "/SMS/VERIFY/"
						+ sessionId + "/" + otp).header("content-type", "application/x-www-form-urlencoded").asString();

			} catch (Throwable e) {
				logger.info("Calling...  2Factor_API Verifying OTP Service was Faild :" + e.getMessage());
				throw new SMSException("The exception is " + e.getMessage());

			}

		} else {
			logger.info("Given Input is Invalid  SessionId: {},OTP {}" + sessionId, otp);
			throw new SMSException("Invaild inputs");
		}
		logger.info("2Factor_API VerifyOtp Service Call Ended : " + response.getStatus());
		return response.getBody();
	}

}
