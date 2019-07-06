package com.mattioda.rodrigo.socialbook.services.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender jms;
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		mailSender.send(msg);
	}
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		jms.send(msg);
	}
	
	@Override
	public void sendNewPasswordEmail(MimeMessage msg) {
		jms.send(msg);
	}


}
