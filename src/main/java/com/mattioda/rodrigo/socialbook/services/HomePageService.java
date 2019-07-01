package com.mattioda.rodrigo.socialbook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mattioda.rodrigo.socialbook.domain.User;
import com.mattioda.rodrigo.socialbook.dto.UserDto;
import com.mattioda.rodrigo.socialbook.repository.UserRepository;
import com.mattioda.rodrigo.socialbook.security.UserSecurity;
import com.mattioda.rodrigo.socialbook.services.email.EmailService;
import com.mattioda.rodrigo.socialbook.services.exception.AuthorizationException;

@Service
public class HomePageService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Page<User> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSecurity userSS= UserLogadoService.authenticated();
		if(userSS==null) {
			throw new AuthorizationException("Acesso negado");
		}		
		PageRequest pageRequest= PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		User user= userService.findById(userSS.getId());
		return userRepository.findByLivrosUsuario(user.getLivrosUsuario(), pageRequest);
	}
	
	public User insert(User user) {
		emailService.sendOrderConfirmationEmail(user);
		return userRepository.insert(user);
	}
	
	public User fromDto(UserDto userDto) {
		return new User(userDto.getId(),userDto.getNome(),
				userDto.getSobrenome(), userDto.getEmail(),
				userDto.getInteresses(), pe.encode(userDto.getSenha()));
	}
}
