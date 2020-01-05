package com.julia.api.catchup.excessao.dto;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class StatusErro {

	private HttpStatus status;
	private String mensagem;
	private List<String> errors;

	public StatusErro(HttpStatus status, String mensagem, List<String> errors) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.errors = errors;
	}

	public StatusErro(HttpStatus status, String mensagem, String error) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMessage(String mensagem) {
		this.mensagem = mensagem;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	
	
}
