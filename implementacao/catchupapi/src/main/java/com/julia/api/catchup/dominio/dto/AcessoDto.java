package com.julia.api.catchup.dominio.dto;


import org.hibernate.validator.constraints.br.CPF;

public class AcessoDto {
	
	
	@CPF
	private String cpf;
	
	private String senha;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	

	
}
