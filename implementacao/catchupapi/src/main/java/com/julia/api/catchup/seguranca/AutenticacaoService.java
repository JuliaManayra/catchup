package com.julia.api.catchup.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.julia.api.catchup.service.UsuarioService;


@Service
public class AutenticacaoService  implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService; 
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		return usuarioService.pesquisaUsuario(username);
	}

}
