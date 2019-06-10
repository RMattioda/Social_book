package com.mattioda.rodrigo.socialbook.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String nome;
	private String sobrenome;
	private String email;
	private Date dataNascimento;
	private String senha;
	private String sexo;
	private Integer telefone;
	private String interesses;
	private String cidade;
	private String estado;
	private String tipoUsuario;
	
	//Para evitar trafego desnecessário na hora de busca de usuário usar o atributo lazy=true
	//DBRef serve para referenciar uma outra coleção, no caso a de livros
	@DBRef(lazy=true)
	private List<Livro> livrosUsuario = new ArrayList<Livro>();
	
	@DBRef(lazy=true)
	private List<Autor> autoresUsuario = new ArrayList<Autor>();
	
	public User() {
	}
	
	public User(String id, String nome, String sobrenome, String email, Date dataNascimento, String senha, String sexo,
			Integer telefone, String interesses, String cidade, String estado, String tipoUsuario) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
		this.sexo = sexo;
		this.telefone = telefone;
		this.interesses = interesses;
		this.cidade = cidade;
		this.estado = estado;
		this.tipoUsuario = tipoUsuario;
	}


	public User(String id, String nome, String sobrenome, String email, String interesses) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.interesses = interesses;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public String getInteresses() {
		return interesses;
	}

	public void setInteresses(String interesses) {
		this.interesses = interesses;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	

	public List<Livro> getLivrosUsuario() {
		return livrosUsuario;
	}

	public void setLivrosUsuario(List<Livro> livrosUsuario) {
		this.livrosUsuario = livrosUsuario;
	}
	

	public List<Autor> getAutoresUsuario() {
		return autoresUsuario;
	}

	public void setAutoresUsuario(List<Autor> autoresUsuario) {
		this.autoresUsuario = autoresUsuario;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
