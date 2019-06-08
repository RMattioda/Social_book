package com.mattioda.rodrigo.socialbook.domain;

import java.io.Serializable;
import java.util.Date;

public class Livro implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nomeLivro;
	private String nomeAutor;
	private String categorias;
	private String nomeEditora;
	private String descricaoLivro;
	private String comentariosLivro;
	private Integer numeroDePaginas;
	private Integer avalizacaoLivro;
	private Date dataPublicacao;
	
	public Livro() {	
	}
	public Livro(Long id, String nomeLivro, String nomeAutor, String categorias, String nomeEditora,
			String descricaoLivro, String comentariosLivro, Integer numeroDePaginas, Integer avalizacaoLivro,
			Date dataPublicacao) {
		super();
		this.id = id;
		this.nomeLivro = nomeLivro;
		this.nomeAutor = nomeAutor;
		this.categorias = categorias;
		this.nomeEditora = nomeEditora;
		this.descricaoLivro = descricaoLivro;
		this.comentariosLivro = comentariosLivro;
		this.numeroDePaginas = numeroDePaginas;
		this.avalizacaoLivro = avalizacaoLivro;
		this.dataPublicacao = dataPublicacao;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeLivro() {
		return nomeLivro;
	}
	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
	public String getNomeAutor() {
		return nomeAutor;
	}
	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
	public String getCategorias() {
		return categorias;
	}
	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}
	public String getNomeEditora() {
		return nomeEditora;
	}
	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}
	public String getDescricaoLivro() {
		return descricaoLivro;
	}
	public void setDescricaoLivro(String descricaoLivro) {
		this.descricaoLivro = descricaoLivro;
	}
	public String getComentariosLivro() {
		return comentariosLivro;
	}
	public void setComentariosLivro(String comentariosLivro) {
		this.comentariosLivro = comentariosLivro;
	}
	public Integer getNumeroDePaginas() {
		return numeroDePaginas;
	}
	public void setNumeroDePaginas(Integer numeroDePaginas) {
		this.numeroDePaginas = numeroDePaginas;
	}
	public Integer getAvalizacaoLivro() {
		return avalizacaoLivro;
	}
	public void setAvalizacaoLivro(Integer avalizacaoLivro) {
		this.avalizacaoLivro = avalizacaoLivro;
	}
	public Date getDataPublicacao() {
		return dataPublicacao;
	}
	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
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
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
