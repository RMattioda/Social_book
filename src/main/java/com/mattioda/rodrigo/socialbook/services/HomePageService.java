package com.mattioda.rodrigo.socialbook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mattioda.rodrigo.socialbook.domain.User;
import com.mattioda.rodrigo.socialbook.repository.UserRepository;
import com.mattioda.rodrigo.socialbook.security.UserSecurity;
import com.mattioda.rodrigo.socialbook.services.exception.AuthorizationException;

@Service
public class HomePageService {

	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public UserService userService;
	
	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSecurity userSS= UserLogadoService.authenticated();
		if(userSS==null) {
			throw new AuthorizationException("Acesso negado");
		}		
		PageRequest pageRequest= PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		User user= userService.findById(userSS.getId());
		return userRepository.findByLivrosUsuario(user.getLivrosUsuario(), pageRequest);
	}
}
