package com.mattioda.rodrigo.socialbook.services.email;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.mattioda.rodrigo.socialbook.domain.User;

public abstract class AbstractEmailService implements EmailService{

	@Value("${email.default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(User user) {
		SimpleMailMessage simpleMailMessage= prepareSimpleMailMessageFromUser(user);
		sendEmail(simpleMailMessage);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromUser(User user) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(user.getEmail());
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject("Bem vindo so Social Book");
		simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
		simpleMailMessage.setText("Bem vindo ao site!");
		return simpleMailMessage;
	}
}
