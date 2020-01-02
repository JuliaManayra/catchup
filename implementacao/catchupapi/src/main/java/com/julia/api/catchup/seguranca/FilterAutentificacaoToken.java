package com.julia.api.catchup.seguranca;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.julia.api.catchup.dominio.view.Usuario;
import com.julia.api.catchup.service.TokenService;
import com.julia.api.catchup.service.UsuarioService;


public class FilterAutentificacaoToken  extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	
	
	
	public FilterAutentificacaoToken(TokenService tokenService, UsuarioService usuarioService) {
		super();
		this.tokenService = tokenService;
		this.usuarioService = usuarioService;
	}





	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		if (tokenService.eTokenValido(token)) {
			autenticarCliente(token);
		}
		filter.doFilter(request, response);
		
	}
	
	private void autenticarCliente(String token) {
		Usuario usuario = usuarioService.pesquisaId(tokenService.getIdUsuario(token));
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
