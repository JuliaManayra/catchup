package com.julia.api.catchup.seguranca;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


import com.julia.api.catchup.dominio.view.Usuario;
import com.julia.api.catchup.service.CriptografiaService;
import com.julia.api.catchup.service.UsuarioService;

@Component
public class AuthenticationProviderService  implements AuthenticationProvider {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private CriptografiaService criptografiaService;
	
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			String login = authentication.getName();
	        String senha = authentication.getCredentials().toString();
	        Usuario usuario = (Usuario) usuarioService.pesquisaUsuario(login);
	        if(senhaCorreta(usuario,senha)) {
	            if (usuarioAtivo(usuario)) {
	            		return autenticacaoService.autentificacaoUsuario(usuario,senha);
	            } else {
	                throw new BadCredentialsException("Este usuario esta desativado.");
	            }
	        }else {
	        	throw new BadCredentialsException("Senha invalida");
	        }
	        
	}

	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private boolean usuarioAtivo(Usuario usuario) {
        if (usuario != null) {
            if (usuario.getStatus() == true) {
                return true;
            }
        }
        return false;
    }
	
	
	private boolean senhaCorreta(Usuario usuario,String senha) {
        if (usuario != null) {
            if (usuario.getSenha().equals(criptografiaService.encriptar(senha))) {
                return true;
            }
        }
        return false;
    }
	
}
