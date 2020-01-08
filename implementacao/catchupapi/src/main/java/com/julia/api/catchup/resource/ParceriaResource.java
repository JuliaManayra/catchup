package com.julia.api.catchup.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julia.api.catchup.dominio.dto.ParceriaEditarDto;
import com.julia.api.catchup.dominio.dto.ParceriaNovoDto;
import com.julia.api.catchup.dominio.dto.ParceriaVisualizarDto;
import com.julia.api.catchup.excessao.SemPermissaoEditarParceriaException;
import com.julia.api.catchup.service.ParceriaService;



@RestController
@RequestMapping(value="/parceria")
public class ParceriaResource {
	
	@Autowired
	private ParceriaService parceriaService;
	

	
	@GetMapping(value = "/pesquisar/{id}")
	public ResponseEntity<ParceriaVisualizarDto> pesquisarId(@PathVariable Integer id) {
		try {
			ParceriaVisualizarDto dto = parceriaService.visualisarParceriaId(id);
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new ParceriaVisualizarDto(), null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping(value = "/novo")
	public ResponseEntity<String> salvarNovo(@RequestBody @Valid  ParceriaNovoDto ParceriaNovo, HttpSession session) {
		try {
			parceriaService.salvar(ParceriaNovo,session);
			return ResponseEntity.ok("Cadastrado com Sucesso!");
		}catch (Exception e) {
			return new ResponseEntity<>("false", null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/editar")
	public ResponseEntity<String> salvarEditar(@RequestBody @Valid  ParceriaEditarDto ParceriaNovo,  HttpSession session) {
		try {
			parceriaService.editar(ParceriaNovo,session);
			return ResponseEntity.ok("Editado com Sucesso!");
		}catch (SemPermissaoEditarParceriaException e) {
			return new ResponseEntity<>("O usuario nao tem permissao de editar esta Parceria", null, HttpStatus.FORBIDDEN);
		}catch (Exception e) {
			return new ResponseEntity<>("false", null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value = "/todos")
	public ResponseEntity<List<ParceriaVisualizarDto>> todosParcerias() {
		try {
			List<ParceriaVisualizarDto> dto = parceriaService.listarTodosParcerias();
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/meus")
	public ResponseEntity<List<ParceriaVisualizarDto>> todosMeusParcerias( HttpSession session) {
		try {
			List<ParceriaVisualizarDto> dto = parceriaService.listarTodosMeusParcerias(session);
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), null, HttpStatus.NOT_FOUND);
		}
	}
}
