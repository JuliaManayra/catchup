package com.julia.api.catchup.implementacao;

import java.util.List;

import com.julia.api.catchup.dominio.Post;
import com.julia.api.catchup.dominio.dto.PostVisualizarDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.VisualizarPorParametroCatchupInterface;
import com.julia.api.catchup.repositorio.PostRepositorio;

public class VisualizarPostPorParametroImplementacao implements VisualizarPorParametroCatchupInterface<PostVisualizarDto, String, PostRepositorio> {

	

	@Override
	public List<PostVisualizarDto> listarTodos(String parametro, PostRepositorio element) {
		MapperCatchupInterface<PostVisualizarDto, Post> mapper= new MapperPostVisualizarImplementacao();
		return mapper.listaEntidadeParaDto(element.findByFuncionarioCpf(parametro));
		
	}

	

}
