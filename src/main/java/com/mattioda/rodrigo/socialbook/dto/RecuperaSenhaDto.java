package com.mattioda.rodrigo.socialbook.dto;

import java.io.Serializable;

public class RecuperaSenhaDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String novaSenha;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNovaSenha() {
		return novaSenha;
	}
	public void setNovaSenha(String novasenha) {
		this.novaSenha = novasenha;
	}
	
	
}
