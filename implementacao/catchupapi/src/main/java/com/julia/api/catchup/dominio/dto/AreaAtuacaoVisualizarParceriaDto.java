package com.julia.api.catchup.dominio.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AreaAtuacaoVisualizarParceriaDto {
	
	@JsonIgnore
	private Integer id;
	
	private String descricao;
	
	List<AreaAtuacaoParceriaVisualizarDto> listaParcerias;
	
	public AreaAtuacaoVisualizarParceriaDto() {
		super();
		
	}
	
	public AreaAtuacaoVisualizarParceriaDto(String descricao) {
		super();
		this.id= null;
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
