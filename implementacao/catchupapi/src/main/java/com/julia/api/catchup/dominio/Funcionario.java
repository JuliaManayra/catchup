package com.julia.api.catchup.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Funcionario implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@CPF
	@Column(unique = true)
	private String cpf;
	
	private String emailRecuperacao;
	
	@JsonIgnore
	private String senha;
	
	@JsonIgnore
	private String senhaConfirmacao;
	
	private String filial;
	
	private Date nascimento;

	@ManyToOne
	@JoinColumn(name="fk_perfil")
	private Perfil perfil;
	
	@JsonIgnore
	@OneToMany(mappedBy="funcionarioCriador", cascade=CascadeType.ALL)
	private List<Aviso> listaAvisos;
	
	@JsonIgnore
	@OneToMany(mappedBy="funcionarioCriador", cascade=CascadeType.ALL)
	private List<Parceria> listaParcerias;
	
	@JsonIgnore
	 @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(name = "curte", 
	      joinColumns = @JoinColumn(name = "fk_funcionario", referencedColumnName = "id"), 
	      inverseJoinColumns = @JoinColumn(name = "fk_post", 
	      referencedColumnName = "id"))
	 private List<Post> listaPostsCurtidos; 
	
	
	private Boolean statusFuncionario;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Aviso> getListaAvisos() {
		return listaAvisos;
	}

	public void setListaAvisos(List<Aviso> listaAvisos) {
		this.listaAvisos = listaAvisos;
	}

	
	
	
	public Boolean getStatusFuncionario() {
		return statusFuncionario;
	}

	public void setStatusFuncionario(Boolean statusFuncionario) {
		this.statusFuncionario = statusFuncionario;
	}

	public List<Parceria> getListaParcerias() {
		return listaParcerias;
	}

	public void setListaParcerias(List<Parceria> listaParcerias) {
		this.listaParcerias = listaParcerias;
	}

	public List<Post> getListaPostsCurtidos() {
		return listaPostsCurtidos;
	}

	public void setListaPostsCurtidos(List<Post> listaPostsCurtidos) {
		this.listaPostsCurtidos = listaPostsCurtidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
