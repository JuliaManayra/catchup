package com.julia.api.catchup.seguranca.web;

import java.io.IOException;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class CatchUpAutenticacaoEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		String jsonString = Json.createObjectBuilder()
                .add("timestamp", System.currentTimeMillis())
                .add("status", 403)
                .add("message", "Nao autorizado").build().toString();
                
                
		 ObjectMapper mapper = new ObjectMapper();
		 response.setContentType("application/json;charset=UTF-8");
		 response.setStatus(403);
		 response.getWriter().write(mapper.writeValueAsString(jsonString));
		
		
	}


}


