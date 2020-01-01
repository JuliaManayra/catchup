package com.julia.api.catchup.dominio.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

public class FuncionarioNovo {
	
	private String nome;
	private String senha;
	private String senhaConfirmacao;
	private String emailRecupercao;
	private Integer perfil;
	
	
	
	@CPF
	private String cpf;
	
	
	public FuncionarioNovo() {
		
	}

	public FuncionarioNovo(String nome, String senha, String senhaConfirmacao, String emailRecupercao, Integer perfil,
			@CPF String cpf) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.senhaConfirmacao = senhaConfirmacao;
		this.emailRecupercao = emailRecupercao;
		this.perfil = perfil;
		this.cpf = cpf;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		this.senhaConfirmacao = senhaConfirmacao;
	}

	public String getEmailRecupercao() {
		return emailRecupercao;
	}

	public void setEmailRecupercao(String emailRecupercao) {
		this.emailRecupercao = emailRecupercao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	
	

}
