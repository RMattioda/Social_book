package com.mattioda.rodrigo.socialbook.dto;

import java.io.Serializable;

import com.mattioda.rodrigo.socialbook.domain.User;

public class AutorPublicacaoDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	
	
	public AutorPublicacaoDto() {
		super();
	}
	public AutorPublicacaoDto(User user) {
		id = user.getId();
		nome = user.getNome();
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
}
