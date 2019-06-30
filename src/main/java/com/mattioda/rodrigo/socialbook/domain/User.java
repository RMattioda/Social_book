package com.mattioda.rodrigo.socialbook.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mattioda.rodrigo.socialbook.domain.enums.TipoUser;

@Document(collection="user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String nome;
	private String sobrenome;
	private String email;
	private Date dataNascimento;
	
	@JsonIgnore
	private String senha;
	private String sexo;
	private Integer telefone;
	
	
	private List<String> interesses;
	
	
	private String cidade;
	private String estado;
	
	
	private Set<Integer> tipoUsuario = new HashSet<>();
	
	//Para evitar trafego desnecessário na hora de busca de usuário usar o atributo lazy=true
	//DBRef serve para referenciar uma outra coleção, no caso a de livros
	//@DBRef(lazy=true)
	private List<Livro> livrosUsuario = new ArrayList<Livro>();
	
//	@DBRef(lazy=true)
//	private List<Autor> autoresUsuario = new ArrayList<Autor>();
	
	public User() {
		addTipoUsuario(TipoUser.USER);
	}
	
	public User(String id, String nome, String sobrenome, String email, Date dataNascimento, String senha, String sexo,
			Integer telefone, List<String> interesses, String cidade, String estado, TipoUser tipoUsuario) {
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
		addTipoUsuario(TipoUser.USER);
	}


	public User(String id, String nome, String sobrenome, String email, List<String> interesses,  String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.interesses = interesses;
		this.senha=senha;
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

	public List<String> getInteresses() {
		return interesses;
	}

	public void setInteresses(List<String> interesses) {
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

	public Set<TipoUser> getTipoUsuario() {
		return tipoUsuario.stream().map(x->TipoUser.toEnum(x)).collect(Collectors.toSet());
	}

	public void addTipoUsuario(TipoUser tipoUser) {
		tipoUsuario.add(tipoUser.getCod());
	}
	

	public List<Livro> getLivrosUsuario() {
		return livrosUsuario;
	}

	public void setLivrosUsuario(List<Livro> livrosUsuario) {
		this.livrosUsuario = livrosUsuario;
	}
	

//	public List<Autor> getAutoresUsuario() {
//		return autoresUsuario;
//	}
//
//	public void setAutoresUsuario(List<Autor> autoresUsuario) {
//		this.autoresUsuario = autoresUsuario;
//	}

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
