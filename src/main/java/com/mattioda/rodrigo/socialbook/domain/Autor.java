package com.mattioda.rodrigo.socialbook.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.mattioda.rodrigo.socialbook.dto.ComentariosDto;

public class Autor implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String nomeAutor;
	private String sobrenomeAutor;
	private String apelidoAutor;
	private Date dataNascimento;
	private Date dataFalecimento;
	private Integer idadeAutor;
	private String nacionalidade;
	
	private List<String> categoriasEscritor;
	
	private List<ComentariosDto> comentarios = new ArrayList<>();
	
	@DBRef(lazy=true)
	private List<Livro> listaLivroAutor= new ArrayList<>();
	
	public Autor() {
	}
	
	public Autor(String id, String nomeAutor, String sobrenomeAutor, String apelidoAutor, Date dataNascimento,
			Date dataFalecimento, Integer idadeAutor, String nacionalidade, List<String> categoriasEscritor) {
		super();
		this.id = id;
		this.nomeAutor = nomeAutor;
		this.sobrenomeAutor = sobrenomeAutor;
		this.apelidoAutor = apelidoAutor;
		this.dataNascimento = dataNascimento;
		this.dataFalecimento = dataFalecimento;
		this.idadeAutor = idadeAutor;
		this.nacionalidade = nacionalidade;
		this.categoriasEscritor = categoriasEscritor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public String getSobrenomeAutor() {
		return sobrenomeAutor;
	}

	public void setSobrenomeAutor(String sobrenomeAutor) {
		this.sobrenomeAutor = sobrenomeAutor;
	}

	public String getApelidoAutor() {
		return apelidoAutor;
	}

	public void setApelidoAutor(String apelidoAutor) {
		this.apelidoAutor = apelidoAutor;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataFalecimento() {
		return dataFalecimento;
	}

	public void setDataFalecimento(Date dataFalecimento) {
		this.dataFalecimento = dataFalecimento;
	}

	public Integer getIdadeAutor() {
		return idadeAutor;
	}

	public void setIdadeAutor(Integer idadeAutor) {
		this.idadeAutor = idadeAutor;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public List<String> getCategoriasEscritor() {
		return categoriasEscritor;
	}

	public void setCategoriasEscritor(List<String> categoriasEscritor) {
		this.categoriasEscritor = categoriasEscritor;
	}
	

	public List<Livro> getListaLivroAutor() {
		return listaLivroAutor;
	}

	public void setListaLivroAutor(List<Livro> listaLivroAutor) {
		this.listaLivroAutor = listaLivroAutor;
	}
	

	public List<ComentariosDto> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentariosDto> comentarios) {
		this.comentarios = comentarios;
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
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
