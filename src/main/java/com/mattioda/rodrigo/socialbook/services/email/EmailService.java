package com.mattioda.rodrigo.socialbook.services.email;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.mattioda.rodrigo.socialbook.domain.User;

public interface EmailService {

	//email simples
	void sendOrderConfirmationEmail(User user);
	
	void sendEmail(SimpleMailMessage msg);
	
	//email de confirmação customizado
	void sendHtmlEmail(MimeMessage msg);

	void sendOrderHtmlConfirmationEmail(User user);
	
	
	//email de reenvio de senha
	void sendNewPasswordEmail(MimeMessage msg);
	
	void sendOrderNewPassword(User user, String novaSenha);

	
}
