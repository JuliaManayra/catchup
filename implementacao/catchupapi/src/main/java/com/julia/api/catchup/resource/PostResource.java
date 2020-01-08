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

import com.julia.api.catchup.dominio.dto.PostEditarDto;
import com.julia.api.catchup.dominio.dto.PostNovoDto;
import com.julia.api.catchup.dominio.dto.PostVisualizarDto;
import com.julia.api.catchup.excessao.RecursoNaoEncontradoException;
import com.julia.api.catchup.excessao.SemPermissaoEditarPostException;
import com.julia.api.catchup.service.PostService;



@RestController
@RequestMapping(value="/post")
public class PostResource {
	
	@Autowired
	private PostService postService;
	

	
	@GetMapping(value = "/pesquisar/{id}")
	public ResponseEntity<PostVisualizarDto> pesquisarId(@PathVariable Integer id) {
		try {
			PostVisualizarDto dto = postService.visualisarPostId(id);
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new PostVisualizarDto(), null, HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping(value = "/novo")
	public ResponseEntity<String> salvarNovo(@RequestBody @Valid  PostNovoDto PostNovo, HttpSession session) {
		try {
			postService.salvar(PostNovo,session);
			return ResponseEntity.ok("Cadastrado com Sucesso!");
		}catch (Exception e) {
			return new ResponseEntity<>("false", null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/editar")
	public ResponseEntity<String> salvarEditar(@RequestBody @Valid  PostEditarDto PostNovo,  HttpSession session) {
		try {
			postService.editar(PostNovo,session);
			return ResponseEntity.ok("Editado com Sucesso!");
		}catch (SemPermissaoEditarPostException e) {
			return new ResponseEntity<>("O usuario nao tem permissao de editar este Post, pois nao e o dono!", null, HttpStatus.FORBIDDEN);
		}catch (Exception e) {
			return new ResponseEntity<>("false", null, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/curtir/{id}")
	public ResponseEntity<String> salvarCurtir(@PathVariable("id")  Integer id,  HttpSession session) {
		try {
			 String saida = postService.curtirPost(id, session);
			return ResponseEntity.ok(saida +" com Sucesso!");
		}catch (RecursoNaoEncontradoException e) {
			return new ResponseEntity<>("Post nao encontrado", null, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			return new ResponseEntity<>("false", null, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value = "/todos")
	public ResponseEntity<List<PostVisualizarDto>> todosPosts() {
		try {
			List<PostVisualizarDto> dto = postService.listarTodosPosts();
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/meus")
	public ResponseEntity<List<PostVisualizarDto>> todosMeusPosts( HttpSession session) {
		try {
			List<PostVisualizarDto> dto = postService.listarTodosMeusPosts(session);
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), null, HttpStatus.NOT_FOUND);
		}
	}
}
