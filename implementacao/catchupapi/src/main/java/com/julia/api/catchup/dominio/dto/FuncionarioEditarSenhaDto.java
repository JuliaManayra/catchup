package com.julia.api.catchup.dominio.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FuncionarioEditarSenhaDto {
	
	@JsonIgnore
	private Integer id;
	
	
	private String senha;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private String senhaConfirmacao;
	
	
	
	public FuncionarioEditarSenhaDto(String senha, String senhaConfirmacao) {
		super();
		this.senha = senha;
		this.senhaConfirmacao = senhaConfirmacao;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	public String getSenhaConfirmacao() {
		return senhaConfirmacao;
	}
	public void setSenhaConfirmacao(String senhaConfirmacao) {
		this.senhaConfirmacao =  senhaConfirmacao;
	}
	
	

}
