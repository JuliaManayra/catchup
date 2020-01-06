package com.julia.api.catchup.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.julia.api.catchup.dominio.view.Usuario;
import com.julia.api.catchup.excessao.RecursoNaoEncontradoException;
import com.julia.api.catchup.repositorio.UsuarioRepositorio;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepositorio repositorio;
	
	public UserDetails pesquisaUsuario(String cpf) {
		cpf = mascaraCpf(cpf);
		Optional<Usuario> usuarioOptional = repositorio.findByCpf(cpf);
		if(usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}
		throw new RecursoNaoEncontradoException("Usuário não encontrado. Verifique o CPF.");
		
	}
	
	public Usuario pesquisaId(Integer id) {
		Optional<Usuario> usuarioOptional = repositorio.findById(id);
		if(usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}
		return new Usuario();
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
