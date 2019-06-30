package com.mattioda.rodrigo.socialbook.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mattioda.rodrigo.socialbook.dto.AutorPublicacaoDto;
import com.mattioda.rodrigo.socialbook.dto.ComentariosDto;

@Document
public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String nomeLivro;
	private String nomeAutor;
	private String categorias;
	private String nomeEditora;
	private String descricaoLivro;
	private Integer numeroDePaginas;
	private Integer avalizacaoLivro;
	private Date dataPublicacao;
	private Date dataCriacao;
	private AutorPublicacaoDto autorDaPublicacao;

	private List<ComentariosDto> comentarios = new ArrayList<>();

	public Livro() {
	}

	public Livro(String id, String nomeLivro, String nomeAutor, String categorias, String nomeEditora,
			String descricaoLivro, Integer numeroDePaginas, Integer avalizacaoLivro, Date dataPublicacao,
			Date dataCriacao, AutorPublicacaoDto autorDaPublicacao) {
		super();
		this.id = id;
		this.nomeLivro = nomeLivro;
		this.nomeAutor = nomeAutor;
		this.categorias = categorias;
		this.nomeEditora = nomeEditora;
		this.descricaoLivro = descricaoLivro;
		this.numeroDePaginas = numeroDePaginas;
		this.avalizacaoLivro = avalizacaoLivro;
		this.dataPublicacao = dataPublicacao;
		this.dataCriacao = dataCriacao;
		this.autorDaPublicacao=autorDaPublicacao;
	}

	public Livro(String id, String nomeLivro, String nomeAutor, String categorias, String nomeEditora,
			String descricaoLivro, Date dataCriacao, AutorPublicacaoDto autorDaPublicacao) {
		this.id = id;
		this.nomeLivro = nomeLivro;
		this.nomeAutor = nomeAutor;
		this.categorias = categorias;
		this.nomeEditora = nomeEditora;
		this.descricaoLivro = descricaoLivro;
		this.dataCriacao = dataCriacao;
		this.autorDaPublicacao=autorDaPublicacao;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public AutorPublicacaoDto getAutorDaPublicacao() {
		return autorDaPublicacao;
	}

	public void setAutorDaPublicacao(AutorPublicacaoDto autorDaPublicacao) {
		this.autorDaPublicacao = autorDaPublicacao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
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
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;

	}
}