package com.julia.api.catchup.dominio.dto;

import java.util.Date;

public class AvisoEditarDto {

	private Integer id;
	private Date data;
	private String imagem;
	
	private String titulo;
	
	private String descricao;
	

	
	
	
	public AvisoEditarDto(Integer id, Date data, String imagem, String titulo, String descricao) {
		super();
		this.data = data;
		this.imagem = imagem;
		this.titulo = titulo;
		this.descricao = descricao;
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	
	
	
	
}
