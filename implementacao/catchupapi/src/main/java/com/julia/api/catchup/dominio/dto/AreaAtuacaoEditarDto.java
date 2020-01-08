package com.julia.api.catchup.dominio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AreaAtuacaoEditarDto {
	
	@JsonIgnore
	private Integer id;
	
	private String descricao;
	
	
	
	public AreaAtuacaoEditarDto(String descricao) {
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
