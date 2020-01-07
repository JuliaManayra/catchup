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

import com.julia.api.catchup.dominio.dto.AvisoEditarDto;
import com.julia.api.catchup.dominio.dto.AvisoNovoDto;
import com.julia.api.catchup.dominio.dto.AvisoVisualizarDto;
import com.julia.api.catchup.excessao.SemPermissaoEditarAvisoException;
import com.julia.api.catchup.service.AvisoService;



@RestController
@RequestMapping(value="/aviso")
public class AvisoResource {
	
	@Autowired
	private AvisoService avisoService;
	

	
	@GetMapping(value = "/pesquisar/{id}")
	public ResponseEntity<AvisoVisualizarDto> pesquisarId(@PathVariable Integer id) {
		try {
			AvisoVisualizarDto dto = avisoService.visualisarAvisoId(id);
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new AvisoVisualizarDto(), null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping(value = "/novo")
	public ResponseEntity<String> salvarNovo(@RequestBody @Valid  AvisoNovoDto AvisoNovo) {
		try {
			avisoService.salvar(AvisoNovo);
			return ResponseEntity.ok("Cadastrado com Sucesso!");
		}catch (Exception e) {
			return new ResponseEntity<>("false", null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/editar")
	public ResponseEntity<String> salvarEditar(@RequestBody @Valid  AvisoEditarDto AvisoNovo,  HttpSession session) {
		try {
			 avisoService.editar(AvisoNovo,session);
			return ResponseEntity.ok("Editado com Sucesso!");
		}catch (SemPermissaoEditarAvisoException e) {
			return new ResponseEntity<>("O usuario nao tem permissao de editar este aviso, pois nao e o dono!", null, HttpStatus.FORBIDDEN);
		}catch (Exception e) {
			return new ResponseEntity<>("false", null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
	@GetMapping(value = "/todos")
	public ResponseEntity<List<AvisoVisualizarDto>> todosAvisos() {
		try {
			List<AvisoVisualizarDto> dto = avisoService.listarTodosAvisos();
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), null, HttpStatus.NOT_FOUND);
		}
	}
}
