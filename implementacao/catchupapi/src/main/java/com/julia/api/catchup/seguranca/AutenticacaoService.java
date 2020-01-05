package com.julia.api.catchup.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.julia.api.catchup.dominio.dto.AcessoDto;
import com.julia.api.catchup.dominio.view.Usuario;
import com.julia.api.catchup.service.UsuarioService;


@Service
public class AutenticacaoService  implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService; 
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		return usuarioService.pesquisaUsuario(username);
	}

	public UsernamePasswordAuthenticationToken autentificacaoUsuario(AcessoDto acessoDto) {
		return new UsernamePasswordAuthenticationToken(acessoDto.getCpf(), acessoDto.getSenha());
	}
	
	public UsernamePasswordAuthenticationToken autentificacaoUsuario(Usuario usuario) {
		return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
	}
}
