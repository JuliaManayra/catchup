package com.julia.api.catchup.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.julia.api.catchup.dominio.dto.AcessoDto;
import com.julia.api.catchup.dominio.dto.TokenDto;
import com.julia.api.catchup.excessao.RecursoNaoEncontradoException;
import com.julia.api.catchup.seguranca.AutenticacaoService;
import com.julia.api.catchup.service.TokenService;
import javax.servlet.http.HttpSession;

@RestController
public class AcessoResource {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
		@RequestMapping(value = "", method = RequestMethod.GET)
		public ModelAndView swagger() {
			return new ModelAndView("redirect:/swagger-ui.html");
		}
	
		@RequestMapping(value = "/acesso", method = RequestMethod.POST)
		public ResponseEntity<String> acesso(@RequestBody @Valid AcessoDto acesso, HttpSession session) {
			UsernamePasswordAuthenticationToken permissaoAcesso = autenticacaoService.autentificacaoUsuario(acesso);
		
			try {
				Authentication authentication = authManager.authenticate(permissaoAcesso);
				String token = tokenService.getToken(authentication);
				TokenDto dto = new TokenDto(token, "Bearer");
				session.setAttribute("cpf", autenticacaoService.getCpfUsuarioLogado(acesso.getCpf()));
				return ResponseEntity.ok(dto.toString());
				
			}catch (DisabledException e) {
				
				return  new ResponseEntity<>("Acesso negado!\nEste usuário está inativo.", null, HttpStatus.UNAUTHORIZED); 
			}catch(RecursoNaoEncontradoException e) {
				return new ResponseEntity<>("Acesso negado!\nLogin invalido, verifique o CPF \n", null, HttpStatus.NOT_FOUND);
			}catch (BadCredentialsException e) {
				
				return new ResponseEntity<>("Acesso negado!\nLogin invalido, verifique  a senha \n", null, HttpStatus.FORBIDDEN);
			}	
			catch (AuthenticationException e) {
				return ResponseEntity.badRequest().build();
			}
		
		}
	
}
