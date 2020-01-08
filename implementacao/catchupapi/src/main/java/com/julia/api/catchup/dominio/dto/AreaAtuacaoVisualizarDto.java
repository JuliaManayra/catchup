package com.julia.api.catchup.dominio.dto;

public class AreaAtuacaoVisualizarDto {
	
	private Integer id;
	
	private String descricao;
	
	public AreaAtuacaoVisualizarDto() {
		super();
		
	}
	
	public AreaAtuacaoVisualizarDto(Integer id, String descricao) {
		super();
		this.id= id;
		this.descricao = descricao;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
