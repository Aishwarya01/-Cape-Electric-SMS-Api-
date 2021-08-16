package com.capeelectric.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Component
public class SMSConfig {

	@Value("${api.key}")
	private String apiKey;

	@Value("${otp.type}")
	private String otpType;

	@Value("${template.Name}")
	private String templateName;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getOtpType() {
		return otpType;
	}

	public void setOtpType(String otpType) {
		this.otpType = otpType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

}
