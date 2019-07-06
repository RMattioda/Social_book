package com.mattioda.rodrigo.socialbook.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mattioda.rodrigo.socialbook.domain.User;
import com.mattioda.rodrigo.socialbook.repository.UserRepository;
import com.mattioda.rodrigo.socialbook.services.email.EmailService;
import com.mattioda.rodrigo.socialbook.services.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private UserRepository repo;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	public void reenviarPassword(String email) {
		User user = repo.findByEmail(email);
		if (user == null) {
			throw new ObjectNotFoundException("E-mail não encontrado");
		}

		String novaSenha = newPassword();
		user.setSenha(pe.encode(novaSenha));

		repo.save(user);
		emailService.sendOrderNewPassword(user, novaSenha);
	}

	private String newPassword() {
		int length = 10;

		boolean useLetters = true;

		boolean useNumbers = true;

		String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

		return generatedString;

	}
}
