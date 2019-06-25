package com.mattioda.rodrigo.socialbook.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//para que possa ser injetada como componente
@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	//Método para gerar um token, pegando o usuário, quando expira, e o algoritmo de assinatura + o segredo do .properties
	//Usar o getBytes no secret pq, ele pega um array de bytes, o que necessita a conversão
	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis()+ expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}

	public boolean tokenValido(String token) {
		//Armazena as reinvindicações do token(usuário e tempo de expiração)
		//Ela alega que é o usuário e mostra o seu tempo de expiração
		Claims claims= getClaims(token);
		if(claims!=null) {
			String username= claims.getSubject();
			Date expirationDate= claims.getExpiration();
			Date dateNow= new Date(System.currentTimeMillis());
			//verifica se tem no cabeçalho username e compara a data atual é menor que a data de expiração
			if(username!= null &&
					expirationDate!=null &&
					dateNow.before(expirationDate)) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

	private Claims getClaims(String token) {
		//token = token.substring(7);
		try {			
			//Token era inválido ou nulo
			return Jwts.parser()
					.setSigningKey(secret.getBytes())
					.parseClaimsJws(token).getBody();
			
		}catch(Exception e){
			return null;
		}
	}

	public String getUsername(String token) {
		Claims claims= getClaims(token);
		if(claims!=null) {
			return claims.getSubject();
		}else {
			return null;
		}
	}
}
