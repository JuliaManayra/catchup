package com.julia.api.catchup.dominio.dto;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class FuncionarioEditarDto {
	
	
	
	private Integer id;
	private String nome;
	
	
	private Integer idPerfil;
	
	@Email
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "email@email")
	private String emailRecuperacao;
	
	private String filial;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date nascimento;
	
	
	@CPF
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "000.000.000-00")
	private String cpf;
	
	@JsonIgnore
	private Boolean status = true;
	
	public FuncionarioEditarDto() {
		
	}

	public FuncionarioEditarDto(Integer id, String nome, Integer idPerfil,
			@CPF String cpf) {
		super();
		this.nome = nome;
		
		this.idPerfil = idPerfil;
		this.cpf = mascaraCpf(cpf);
		this.id =id;
	}

	
	
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		this.cpf = mascaraCpf(cpf);
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	private Boolean verificaCpf(String cpf ) {
		Pattern pattern =  Pattern.compile("(^(\\d{3}.\\d{3}.\\d{3}-\\d{2}))");
		Matcher matcher = pattern.matcher(cpf);
		return matcher.matches();
	}
	
	private String mascaraCpf(String cpf) {
		
		 if (verificaCpf(cpf)==false && cpf.length()==11) {
			 return cpf.substring(0,3)+"."+cpf.substring(3,6)+"."+cpf.substring(6,9)+"-"+cpf.substring(9,11);
		 }
		return cpf;
	}
	
	

}
