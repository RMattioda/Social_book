package com.mattioda.rodrigo.socialbook.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.mattioda.rodrigo.socialbook.security.UserSecurity;

public class UserLogadoService {

	//Retorna o usuário que estiver logado no sistema
	public static UserSecurity authenticated() {
		try {
			return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();			
		}catch(Exception e) {
			return null;
		}
	}
}
