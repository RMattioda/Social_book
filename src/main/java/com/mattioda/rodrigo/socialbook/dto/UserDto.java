package com.mattioda.rodrigo.socialbook.dto;

import java.io.Serializable;

import com.mattioda.rodrigo.socialbook.domain.User;

public class UserDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String nome;
	private String sobrenome;
	private String email;	
	private String interesses;
	
	public UserDto() {
	}
	public UserDto(User user) {
		id = user.getId();
		nome = user.getNome();
		sobrenome = user.getSobrenome();
		email = user.getEmail();
		interesses = user.getInteresses();
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
	public String getInteresses() {
		return interesses;
	}
	public void setInteresses(String interesses) {
		this.interesses = interesses;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDto other = (UserDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
	
}
