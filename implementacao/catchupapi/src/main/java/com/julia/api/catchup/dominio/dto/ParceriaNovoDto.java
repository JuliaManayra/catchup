package com.julia.api.catchup.dominio.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ParceriaNovoDto {

	@JsonIgnore
	private Integer id;
	@JsonIgnore
	private Date data;

	private String imagem;

	private String titulo;

	private String descricao;

	@JsonIgnore
	private Integer idFuncionario;


	private String telefone;

	private String endereco;

	private Integer idAreaAtuacao;
	
	
	public ParceriaNovoDto(String imagem, String titulo, String descricao, Integer idFuncionario, String telefone,
			String endereco, Integer idAreaAtuacao) {
		super();
		this.imagem = imagem;
		this.titulo = titulo;
		this.descricao = descricao;
		this.idFuncionario = idFuncionario;
		this.telefone = telefone;
		this.endereco = endereco;
		this.idAreaAtuacao = idAreaAtuacao;
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

	public Integer getIdAreaAtuacao() {
		return idAreaAtuacao;
	}

	public void setIdAreaAtuacao(Integer idAreaAtuacao) {
		this.idAreaAtuacao = idAreaAtuacao;
	}

	

}
