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
import com.mattioda.rodrigo.socialbook.dto.RecuperaSenhaDto;

public abstract class AbstractEmailService implements EmailService {

	@Value("${email.default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	/*
	 * Envio de email padrão sem html Template
	 * 
	 */
	@Override
	public void sendOrderConfirmationEmail(User user) {
		SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromUser(user);
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

	/*
	 * Envio de email customizado de solicitação de nova senha
	 * 
	 */
	@Override
	public void sendOrderNewPassword(User user, String novaSenha) {
		try {
			MimeMessage mailMessage = prepareMimeMassegeNewPass(user, novaSenha);
			sendNewPasswordEmail(mailMessage);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(user);
		}
	}

	protected MimeMessage prepareMimeMassegeNewPass(User user, String novaSenha) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
		mimeMessageHelper.setTo(user.getEmail());
		mimeMessageHelper.setFrom(sender);
		mimeMessageHelper.setSubject("Recuperação de senha");
		mimeMessageHelper.setText(htmlPassRecoveryFromTemplate(user, novaSenha), true);
		return mimeMessage;
	}

	protected String htmlPassRecoveryFromTemplate(User user, String novaSenha) {
		Context context = new Context();
		RecuperaSenhaDto recuperaSenha= new RecuperaSenhaDto();
		recuperaSenha=this.pegarDto(user, novaSenha);
		context.setVariable("recuperaSenha", recuperaSenha);
		return templateEngine.process("email/novaSenha", context);
	}
	
	private RecuperaSenhaDto pegarDto(User user, String novaSenha) {
		RecuperaSenhaDto recuperaSenhaDto= new RecuperaSenhaDto();
		recuperaSenhaDto.setEmail(user.getEmail());
		recuperaSenhaDto.setNovaSenha(novaSenha);
		return recuperaSenhaDto;
	}

	/*
	 * Envio de email customizado de confirmação
	 * 
	 */
	@Override
	public void sendOrderHtmlConfirmationEmail(User user) {
		try {
			MimeMessage mailMessage = prepareMimeMassege(user);
			sendHtmlEmail(mailMessage);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(user);
		}
	}

	protected MimeMessage prepareMimeMassege(User user) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
		mimeMessageHelper.setTo(user.getEmail());
		mimeMessageHelper.setFrom(sender);
		mimeMessageHelper.setSubject("Bem vindo ao Social Book!");
		mimeMessageHelper.setText(htmlFromTemplate(user), true);
		return mimeMessage;
	}

	protected String htmlFromTemplate(User user) {
		Context context = new Context();
		context.setVariable("user", user);
		return templateEngine.process("email/confirmationEmail", context);
	}

}
