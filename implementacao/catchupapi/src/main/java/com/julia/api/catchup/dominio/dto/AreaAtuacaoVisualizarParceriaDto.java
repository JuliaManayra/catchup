package com.julia.api.catchup.dominio.dto;

import java.util.List;

public class AreaAtuacaoVisualizarParceriaDto {
	
//	@JsonIgnore
	private Integer id;
	
	private String descricao;
	
	List<AreaAtuacaoParceriaVisualizarDto> listaParcerias;
	
	public AreaAtuacaoVisualizarParceriaDto() {
		super();
		
	}
	
	public AreaAtuacaoVisualizarParceriaDto(Integer id, String descricao) {
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

	public List<AreaAtuacaoParceriaVisualizarDto> getListaParcerias() {
		return listaParcerias;
	}

	public void setListaParcerias(List<AreaAtuacaoParceriaVisualizarDto> listaParcerias) {
		this.listaParcerias = listaParcerias;
	}
	
	
	
}
