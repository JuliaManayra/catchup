package com.julia.api.catchup.dominio.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AvisoNovoDto {

	@JsonIgnore
	private Integer id;
	@JsonIgnore
	private Date data;
	
	private String imagem;
	
	private String titulo;
	
	private String descricao;
	
	@JsonIgnore
	private Integer idFuncionario;

	
	
	
	public AvisoNovoDto(String imagem, String titulo, String descricao) {
		super();
		this.data = new Date();
		this.imagem = imagem;
		this.titulo = titulo;
		this.descricao = descricao;
		this.id = null;
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

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	

	
	
	
	
	
}
