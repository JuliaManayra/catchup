package com.julia.api.catchup.dominio.dto;

import java.sql.Date;

import org.hibernate.validator.constraints.br.CPF;

import com.julia.api.catchup.dominio.validacao.Senha;

@Senha
public class FuncionarioVisualizarDto {
	
	private String nome;

	private String emailRecupercao;
	private Integer perfil;
	
	private String emailRecuperacao;
	
	private String filial;
	
	private Date nascimento;
	
	
	@CPF
	private String cpf;
	
	
	public FuncionarioVisualizarDto() {
		
	}

	

	public FuncionarioVisualizarDto(String nome, String emailRecupercao, Integer perfil, String emailRecuperacao,
			String filial, Date nascimento, @CPF String cpf) {
		super();
		this.nome = nome;
		this.emailRecupercao = emailRecupercao;
		this.perfil = perfil;
		this.emailRecuperacao = emailRecuperacao;
		this.filial = filial;
		this.nascimento = nascimento;
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

	public String getEmailRecuperacao() {
		return emailRecuperacao;
	}

	public void setEmailRecuperacao(String emailRecuperacao) {
		this.emailRecuperacao = emailRecuperacao;
	}

	public String getFilial() {
		return filial;
	}

	public void setFilial(String filial) {
		this.filial = filial;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	
	
	
	

}
