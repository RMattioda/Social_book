package com.mattioda.rodrigo.socialbook.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mattioda.rodrigo.socialbook.domain.User;

public class UserDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private String sobrenome;
	
	@Email
	private String email;	
	
	@JsonIgnore
	@NotEmpty
	private String senha;
	
	private List<String> interesses;
	
	public UserDto() {
	}
	public UserDto(User user) {
		id = user.getId();
		nome = user.getNome();
		sobrenome = user.getSobrenome();
		email = user.getEmail();
		interesses = user.getInteresses();
		senha= user.getSenha();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getInteresses() {
		return interesses;
	}
	public void setInteresses(List<String> interesses) {
		this.interesses = interesses;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
