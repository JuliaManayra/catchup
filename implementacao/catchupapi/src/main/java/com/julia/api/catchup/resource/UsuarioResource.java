package com.julia.api.catchup.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import com.julia.api.catchup.dominio.dto.FuncionarioEditarDto;
import com.julia.api.catchup.dominio.dto.FuncionarioEditarSenhaDto;
import com.julia.api.catchup.dominio.dto.FuncionarioNovoDto;
import com.julia.api.catchup.dominio.dto.FuncionarioVisualizarDto;
import com.julia.api.catchup.service.FuncionarioService;



@RestController
@RequestMapping(value="/usuario")
public class UsuarioResource {
	
	@Autowired
	private FuncionarioService funcionarioService;
	

	
	@GetMapping(value = "/pesquisar/{id}")
	public ResponseEntity<FuncionarioVisualizarDto> pesquisarId(@PathVariable Integer id) {
		try {
			FuncionarioVisualizarDto dto = funcionarioService.visualisarFuncionarioId(id);
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new FuncionarioVisualizarDto(), null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping(value = "/novo")
	public ResponseEntity<String> salvarNovo(@RequestBody @Valid  FuncionarioNovoDto funcionarioNovo) {
		try {
			 funcionarioService.salvar(funcionarioNovo);
			return ResponseEntity.ok("Cadastrado com Sucesso!");
		}catch(DataIntegrityViolationException e) {
			 String erro = e.getRootCause().getMessage();
			 String[] er= erro.split("=");
			 String resultado = er[1];
			 resultado = resultado.replaceAll("already exists", "ja cadastrado");
			return new ResponseEntity<>(resultado, null, HttpStatus.CONFLICT);
		}catch (Exception e) {
			return new ResponseEntity<>("false", null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/editar")
	public ResponseEntity<String> salvarEditar(@RequestBody @Valid  FuncionarioEditarDto funcionarioNovo) {
		try {
			 funcionarioService.editar(funcionarioNovo);
			return ResponseEntity.ok("Editado com Sucesso!");
		}catch (Exception e) {
			return new ResponseEntity<>("false", null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PutMapping(value = "/alterarSenha")
	public ResponseEntity<String> alterarSenha(@RequestBody @Valid  FuncionarioEditarSenhaDto funcionarioNovo, HttpSession session) {
		try {
			
			 funcionarioService.alterarSenha(funcionarioNovo,session);
			return ResponseEntity.ok("Senha alterada com Sucesso!");
		}catch (Exception e) {
			return new ResponseEntity<>("false", null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value = "/todos")
	public ResponseEntity<List<FuncionarioVisualizarDto>> todosFuncionarios() {
		try {
			List<FuncionarioVisualizarDto> dto = funcionarioService.listarTodosFuncionarios();
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), null, HttpStatus.NOT_FOUND);
		}
	}
}
