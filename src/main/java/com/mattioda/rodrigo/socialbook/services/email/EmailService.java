package com.mattioda.rodrigo.socialbook.services.email;

import org.springframework.mail.SimpleMailMessage;

import com.mattioda.rodrigo.socialbook.domain.User;

public interface EmailService {

	void sendOrderConfirmationEmail(User user);
	
	void sendEmail(SimpleMailMessage msg);
}
