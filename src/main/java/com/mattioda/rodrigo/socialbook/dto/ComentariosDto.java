package com.mattioda.rodrigo.socialbook.dto;

import java.io.Serializable;
import java.util.Date;

public class ComentariosDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private String texto;
	private Date dataPublicacao;
	private AutorPublicacaoDto autor;
	
	public ComentariosDto() {
		
	}

	public ComentariosDto(String texto, Date dataPublicacao, AutorPublicacaoDto autor) {
		super();
		this.texto = texto;
		this.dataPublicacao = dataPublicacao;
		this.autor = autor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public AutorPublicacaoDto getAutor() {
		return autor;
	}

	public void setAutor(AutorPublicacaoDto autor) {
		this.autor = autor;
	}
	
}
