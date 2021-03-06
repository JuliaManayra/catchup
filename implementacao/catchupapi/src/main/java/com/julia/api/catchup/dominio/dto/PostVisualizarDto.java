package com.julia.api.catchup.dominio.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PostVisualizarDto {
	
	private Integer id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss",timezone="GMT-3")
	private Date data;
	
	private String imagem;
	
	private String titulo;
	
	private String descricao;
	
	private String nomeFuncionarioCriador;
	
	private String filialFuncionarioCriador;

	private List<PostFuncionarioCurtidaVisualizarDto> listaFuncionariosCurtidores;
	
	public PostVisualizarDto() {
		
	}
	
	public PostVisualizarDto(Integer id, Date data, String imagem, String titulo, String descricao,
			String nomeFuncionarioCriador, String filialFuncionarioCriador) {
		super();
		this.id = id;
		this.data = data;
		this.imagem = imagem;
		this.titulo = titulo;
		this.descricao = descricao;
		this.nomeFuncionarioCriador = nomeFuncionarioCriador;
		this.filialFuncionarioCriador = filialFuncionarioCriador;
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

	public String getNomeFuncionarioCriador() {
		return nomeFuncionarioCriador;
	}

	public void setNomeFuncionarioCriador(String nomeFuncionarioCriador) {
		this.nomeFuncionarioCriador = nomeFuncionarioCriador;
	}

	public String getFilialFuncionarioCriador() {
		return filialFuncionarioCriador;
	}

	public void setFilialFuncionarioCriador(String filialFuncionarioCriador) {
		this.filialFuncionarioCriador = filialFuncionarioCriador;
	}

	public List<PostFuncionarioCurtidaVisualizarDto> getListaFuncionariosCurtidores() {
		return listaFuncionariosCurtidores;
	}

	public void setListaFuncionariosCurtidores(List<PostFuncionarioCurtidaVisualizarDto> listaFuncionariosCurtidores) {
		this.listaFuncionariosCurtidores = listaFuncionariosCurtidores;
	}

	
	
}
