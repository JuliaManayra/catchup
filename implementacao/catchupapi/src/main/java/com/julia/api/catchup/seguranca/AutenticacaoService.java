package com.julia.api.catchup.seguranca;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public UsernamePasswordAuthenticationToken autentificacaoUsuario(Usuario usuario, String senha) {
		return new UsernamePasswordAuthenticationToken(usuario,senha, usuario.getAuthorities());
	}
	
	public UsernamePasswordAuthenticationToken autentificacaoUsuario(Usuario usuario) {
		return new UsernamePasswordAuthenticationToken(usuario,null, usuario.getAuthorities());
	}
	
	public String getCpfUsuarioLogado(String cpf) {
		return mascaraCpf(cpf);
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
