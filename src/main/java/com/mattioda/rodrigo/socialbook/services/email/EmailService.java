package com.mattioda.rodrigo.socialbook.services.email;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.mattioda.rodrigo.socialbook.domain.User;

public interface EmailService {

	void sendOrderConfirmationEmail(User user);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendHtmlEmail(MimeMessage msg);

	void sendOrderHtmlConfirmationEmail(User user);
}
