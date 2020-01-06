package com.julia.api.catchup.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julia.api.catchup.dominio.dto.FuncionarioNovoDto;
import com.julia.api.catchup.dominio.dto.FuncionarioVisualizarDto;
import com.julia.api.catchup.service.FuncionarioService;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioResource {
	
	@Autowired
	private FuncionarioService funcionarioService;
	

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<FuncionarioVisualizarDto> pesquisarId(@PathVariable Integer id) {
		try {
			FuncionarioVisualizarDto dto = funcionarioService.visualisarFuncionarioId(id);
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new FuncionarioVisualizarDto(), null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping(value = "/novo")
	public ResponseEntity<Boolean> salvarNovo(@RequestBody @Valid  FuncionarioNovoDto funcionarioNovo) {
		try {
			Boolean dto = funcionarioService.salvarNovoFuncionario(funcionarioNovo);
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(false, null, HttpStatus.BAD_REQUEST);
		}
	}
}
