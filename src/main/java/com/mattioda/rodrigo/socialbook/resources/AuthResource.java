package com.mattioda.rodrigo.socialbook.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mattioda.rodrigo.socialbook.domain.User;
import com.mattioda.rodrigo.socialbook.security.JWTUtil;
import com.mattioda.rodrigo.socialbook.security.UserSecurity;
import com.mattioda.rodrigo.socialbook.services.AuthService;
import com.mattioda.rodrigo.socialbook.services.UserLogadoService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSecurity user = UserLogadoService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/esqueci_senha", method = RequestMethod.POST)
	public ResponseEntity<Void> esqueciSenha(@Valid @RequestBody User user) {
		authService.reenviarPassword(user.getEmail());
		return ResponseEntity.noContent().build();
	}
}
