package com.julia.api.catchup.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.julia.api.catchup.dominio.view.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.Authentication;


@Service
public class TokenService {

	@Value("${catchup.jwt.expiration}")
	private String expiration;
	
	@Value("${catchup.jwt.secret}")
	private String secret;
	
	public String getToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date data = new Date();
		Date dataExpira = new Date(data.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
				.setIssuer("API CatchUp")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(data)
				.setExpiration(dataExpira)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	
	public boolean eTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Integer getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Integer.parseInt(claims.getSubject());
	}
	
	
}
