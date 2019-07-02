package com.mattioda.rodrigo.socialbook.services.email;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mattioda.rodrigo.socialbook.domain.User;

public abstract class AbstractEmailService implements EmailService{

	@Value("${email.default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(User user) {
		SimpleMailMessage simpleMailMessage= prepareSimpleMailMessageFromUser(user);
		sendEmail(simpleMailMessage);
	}
	//Para texto plano
	protected SimpleMailMessage prepareSimpleMailMessageFromUser(User user) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(user.getEmail());
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject("Bem vindo so Social Book");
		simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
		simpleMailMessage.setText("Bem vindo ao site!");
		return simpleMailMessage;
	}
	
	public void sendOrderHtmlConfirmationEmail(User user) {
		try {
			MimeMessage mailMessage= prepareMimeMassege(user);
			sendHtmlEmail(mailMessage);			
		}catch(MessagingException e) {
			sendOrderConfirmationEmail(user);
		}
	}
	protected MimeMessage prepareMimeMassege(User user) throws MessagingException {
		MimeMessage mimeMessage= javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
		mimeMessageHelper.setTo(user.getEmail());
		mimeMessageHelper.setFrom(sender);
		mimeMessageHelper.setSubject("Bem vindo ao Social Book!");
		mimeMessageHelper.setText(htmlFromTemplate(user), true);
		return mimeMessage;
	}
	
	//Para html email
	protected String htmlFromTemplate(User user) {
		Context context = new Context();
		context.setVariable("user", user);
		return templateEngine.process("email/confirmationEmail", context);
	}
}
