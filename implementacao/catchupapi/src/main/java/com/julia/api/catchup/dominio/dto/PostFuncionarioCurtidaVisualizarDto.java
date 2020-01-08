package com.julia.api.catchup.dominio.dto;

public class PostFuncionarioCurtidaVisualizarDto {
	
	private Integer id;
	
	
	private String nomeFuncionarioCurtida;


	
	public PostFuncionarioCurtidaVisualizarDto() {
		
	}
	
	
	public PostFuncionarioCurtidaVisualizarDto(Integer id, String nomeFuncionarioCurtida) {
		super();
		this.id = id;
		this.nomeFuncionarioCurtida = nomeFuncionarioCurtida;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNomeFuncionarioCurtida() {
		return nomeFuncionarioCurtida;
	}


	public void setNomeFuncionarioCurtida(String nomeFuncionarioCurtida) {
		this.nomeFuncionarioCurtida = nomeFuncionarioCurtida;
	}
	
	

	
	
}
