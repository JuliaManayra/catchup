package com.julia.api.catchup.dominio.dto;



import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;


public class FuncionarioVisualizarDto {
	
	private String nome;

	private Integer idPerfil;
	
	private String emailRecuperacao;
	
	private String filial;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date nascimento;
	
	
	@CPF
	private String cpf;
	
	
	public FuncionarioVisualizarDto() {
		
	}

	

	public FuncionarioVisualizarDto(String nome, Integer idPerfil, String emailRecuperacao,
			String filial, Date nascimento, @CPF String cpf) {
		super();
		this.nome = nome;
		this.idPerfil = idPerfil;
		this.emailRecuperacao = emailRecuperacao;
		this.filial = filial;
		this.nascimento = nascimento;
		this.cpf = cpf;
	}





	public Integer getIdPerfil() {
		return idPerfil;
	}



	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
