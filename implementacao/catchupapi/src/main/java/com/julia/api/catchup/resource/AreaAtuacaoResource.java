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

import com.julia.api.catchup.dominio.dto.AreaAtuacaoEditarDto;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoNovoDto;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoParceriaVisualizarDto;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoVisualizarDto;
import com.julia.api.catchup.excessao.SemPermissaoEditarAreaAtuacaoException;
import com.julia.api.catchup.service.AreaAtuacaoService;



@RestController
@RequestMapping(value="/areaAtuacao")
public class AreaAtuacaoResource {
	
	@Autowired
	private AreaAtuacaoService AreaAtuacaoService;
	

	
	@GetMapping(value = "/pesquisar/{id}")
	public ResponseEntity<AreaAtuacaoVisualizarDto> pesquisarId(@PathVariable Integer id) {
		try {
			AreaAtuacaoVisualizarDto dto = AreaAtuacaoService.visualisarAreaAtuacaoId(id);
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new AreaAtuacaoVisualizarDto(), null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(value = "/pesquisar/parcerias/{idAreaAtuacao}")
	public ResponseEntity<AreaAtuacaoParceriaVisualizarDto> pesquisarParceriasId(@PathVariable("idAreaAtuacao") Integer id) {
		try {
			AreaAtuacaoParceriaVisualizarDto dto = AreaAtuacaoService.visualisarAreaAtuacaoParceriaId(id);
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new AreaAtuacaoParceriaVisualizarDto(), null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/novo")
	public ResponseEntity<String> salvarNovo(@RequestBody @Valid  AreaAtuacaoNovoDto AreaAtuacaoNovo, HttpSession session) {
		try {
			AreaAtuacaoService.salvar(AreaAtuacaoNovo,session);
			return ResponseEntity.ok("Cadastrado com Sucesso!");
		}catch (Exception e) {
			return new ResponseEntity<>("false", null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/editar")
	public ResponseEntity<String> salvarEditar(@RequestBody @Valid  AreaAtuacaoEditarDto AreaAtuacaoNovo,  HttpSession session) {
		try {
			 AreaAtuacaoService.editar(AreaAtuacaoNovo,session);
			return ResponseEntity.ok("Editado com Sucesso!");
		}catch (SemPermissaoEditarAreaAtuacaoException e) {
			return new ResponseEntity<>("O usuario nao tem permissao de editar este AreaAtuacao, pois nao e o dono!", null, HttpStatus.FORBIDDEN);
		}catch (Exception e) {
			return new ResponseEntity<>("false", null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value = "/todos")
	public ResponseEntity<List<AreaAtuacaoVisualizarDto>> todosAreaAtuacaos() {
		try {
			List<AreaAtuacaoVisualizarDto> dto = AreaAtuacaoService.listarTodosAreaAtuacaos();
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), null, HttpStatus.NOT_FOUND);
		}
	}
	
	
}
