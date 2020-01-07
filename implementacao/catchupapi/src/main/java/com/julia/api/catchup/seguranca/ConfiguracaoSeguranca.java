package com.julia.api.catchup.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.julia.api.catchup.seguranca.web.CatchUpAutenticacaoEntryPoint;
import com.julia.api.catchup.seguranca.web.MinhaSolicitacaoAutenticadaSuccessHandler;
import com.julia.api.catchup.seguranca.web.error.CatchUpAcessoNegadoHandler;
import com.julia.api.catchup.seguranca.web.error.FalhaAutenticacaoHandler;
import com.julia.api.catchup.service.TokenService;
import com.julia.api.catchup.service.UsuarioService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.julia.api.catchup.seguranca")
public class ConfiguracaoSeguranca extends WebSecurityConfigurerAdapter {

	@Autowired
	private AutenticacaoService autenticacaoService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private CatchUpAutenticacaoEntryPoint catchUpAutenticacaoEntryPoint;

	@Autowired
	private MinhaSolicitacaoAutenticadaSuccessHandler successoHandler;

	@Autowired
	private CatchUpAcessoNegadoHandler catchUpAcessoNegadoHandler;
	
	@Autowired
	private AuthenticationProviderService authenticationProviderService;

	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
		auth.authenticationProvider(authenticationProviderService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.and()
        .exceptionHandling()
        .accessDeniedHandler(catchUpAcessoNegadoHandler)
        .authenticationEntryPoint(catchUpAutenticacaoEntryPoint)
        .and()
        .authorizeRequests()
		.antMatchers(HttpMethod.GET, "/topicos").permitAll()
		.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
		.antMatchers(HttpMethod.POST, "/acesso").permitAll()
		.antMatchers(HttpMethod.GET, "/usuario/pesquisar/**").hasAnyRole("ADMINISTRADOR","COLABORADOR")
		.antMatchers(HttpMethod.POST, "/usuario/salvar").hasRole("ADMINISTRADOR")
		.antMatchers(HttpMethod.PUT, "/usuario/editar").hasRole("ADMINISTRADOR")
		.antMatchers(HttpMethod.GET, "/usuario/todos").hasRole("ADMINISTRADOR")
		.antMatchers(HttpMethod.PUT, "/alterarSenha").hasAnyRole("ADMINISTRADOR","COLABORADOR")
		.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
		.anyRequest().authenticated()
		.and()
        .formLogin()
        .successHandler(successoHandler)
        .failureHandler(FalhaAutenticacaoHandler())
		.and().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new FilterAutentificacaoToken(tokenService, usuarioService),
						UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**", "/h2/**");
	}

	 @Bean
	 public FalhaAutenticacaoHandler FalhaAutenticacaoHandler() {
	        return new FalhaAutenticacaoHandler();
	 }
	 
}
