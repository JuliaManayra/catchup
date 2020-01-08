package com.julia.api.catchup.dominio.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AreaAtuacaoParceriaVisualizarDto {
	
	private Integer id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss",timezone="GMT-3")
	private Date data;
	
	private String imagem;
	
	private String titulo;
	
	private String descricao;
	
	private String nomeFuncionarioCriador;
	
	private String filialFuncionarioCriador;
	
	
	private String telefone;

	private String endereco;


	
	public AreaAtuacaoParceriaVisualizarDto() {
		
	}
	
	

	public AreaAtuacaoParceriaVisualizarDto(Integer id, Date data, String imagem, String titulo, String descricao,
			String nomeFuncionarioCriador, String filialFuncionarioCriador, String telefone, String endereco,
			Integer idAreaAtuacao) {
		super();
		this.id = id;
		this.data = data;
		this.imagem = imagem;
		this.titulo = titulo;
		this.descricao = descricao;
		this.nomeFuncionarioCriador = nomeFuncionarioCriador;
		this.filialFuncionarioCriador = filialFuncionarioCriador;
		this.telefone = telefone;
		this.endereco = endereco;
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

	

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	
	
}
