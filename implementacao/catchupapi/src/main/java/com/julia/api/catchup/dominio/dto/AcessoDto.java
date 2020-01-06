package com.julia.api.catchup.dominio.dto;


import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AcessoDto {
	
	
	@CPF
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "000.000.000-00")
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
