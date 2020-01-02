package com.julia.api.catchup.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.julia.api.catchup.dominio.view.Usuario;
import com.julia.api.catchup.repositorio.UsuarioRepositorio;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepositorio repositorio;
	
	public UserDetails pesquisaUsuario(String cpf) {
		Optional<Usuario> usuarioOptional = repositorio.findByCpf(cpf);
		if(usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}
		throw new UsernameNotFoundException("Usuário não encontrado. Verifique o CPF.");
		
	}
	
	public Usuario pesquisaId(Integer id) {
		Optional<Usuario> usuarioOptional = repositorio.findById(id);
		if(usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}
		return new Usuario();
	}
	
}
