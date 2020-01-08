package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.Optional;

import com.julia.api.catchup.dominio.Post;
import com.julia.api.catchup.dominio.dto.PostVisualizarDto;
import com.julia.api.catchup.excessao.RecursoNaoEncontradoException;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.VisualizarCatchupInterface;
import com.julia.api.catchup.repositorio.PostRepositorio;

public class VisualizarPostImplementacao implements VisualizarCatchupInterface<PostVisualizarDto, Integer, PostRepositorio> {

	

	@Override
	public List<PostVisualizarDto> listarTodos(PostRepositorio element) {
		MapperCatchupInterface<PostVisualizarDto, Post> mapper= new MapperPostVisualizarImplementacao();
		return mapper.listaEntidadeParaDto(element.findAll());
	}

	@Override
	public PostVisualizarDto pesquisarId(Integer id,PostRepositorio entidade) {
		MapperCatchupInterface<PostVisualizarDto, Post> mapper= new MapperPostVisualizarImplementacao();
		Optional<Post> PostOptional = entidade.findById(id);

		if (PostOptional.isPresent()) {
			return mapper.entidadeParaDto(PostOptional.get());

		}
		
		throw new RecursoNaoEncontradoException("Post nao encontrado");
	}

}
