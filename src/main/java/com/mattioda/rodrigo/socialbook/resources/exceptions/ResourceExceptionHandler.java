package com.mattioda.rodrigo.socialbook.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mattioda.rodrigo.socialbook.services.exception.AuthorizationException;
import com.mattioda.rodrigo.socialbook.services.exception.ObjectNotFoundException;

//Informa para o spring que essa é a classe responsável por tratar possiveis erros na requisição
@ControllerAdvice
public class ResourceExceptionHandler{

	//É usada a anotação no método que faz o tratamento da excessão quando chamada
	//O parâmetro .class é usado para identificar quando for essa excessão, o mesmo deve executar este método em específico
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		HttpStatus status= HttpStatus.NOT_FOUND;
		//Formata os atributos gerados pelo erro usando a classe StandardError
		StandardError erro= new StandardError(System.currentTimeMillis(),status.value(),
				"Não encontrado", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(erro);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
		
		HttpStatus status= HttpStatus.FORBIDDEN;
		StandardError erro= new StandardError(System.currentTimeMillis(),status.value(),
				"Acesso negado", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(erro);
	}
}
