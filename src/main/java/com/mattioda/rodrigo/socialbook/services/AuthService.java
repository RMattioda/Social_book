package com.mattioda.rodrigo.socialbook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mattioda.rodrigo.socialbook.domain.User;
import com.mattioda.rodrigo.socialbook.repository.UserRepository;
import com.mattioda.rodrigo.socialbook.services.exception.ObjectNotFoundException;

//@Service
public class AuthService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void reenviarPassword(String email) {		
		User user = repo.findByEmail(email);
		if(user==null) {
			throw new ObjectNotFoundException("E-mail não encontrado");
		}
		
		String novaSenha= newPassword();
		user.setSenha(pe.encode(novaSenha));
		
		repo.save(user);
	}

	private String newPassword() {
		return null;
	}
}
