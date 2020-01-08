package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.Optional;

import com.julia.api.catchup.dominio.AreaAtuacao;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoVisualizarDto;
import com.julia.api.catchup.excessao.RecursoNaoEncontradoException;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.VisualizarCatchupInterface;
import com.julia.api.catchup.repositorio.AreaAtuacaoRepositorio;

public class VisualizarAreaAtuacaoImplementacao implements VisualizarCatchupInterface<AreaAtuacaoVisualizarDto, Integer, AreaAtuacaoRepositorio> {

	

	@Override
	public List<AreaAtuacaoVisualizarDto> listarTodos(AreaAtuacaoRepositorio element) {
		MapperCatchupInterface<AreaAtuacaoVisualizarDto, AreaAtuacao> mapper= new MapperAreaAtuacaoVisualizarImplementacao();
		return mapper.listaEntidadeParaDto(element.findAll());
	}

	@Override
	public AreaAtuacaoVisualizarDto pesquisarId(Integer id,AreaAtuacaoRepositorio entidade) {
		MapperCatchupInterface<AreaAtuacaoVisualizarDto, AreaAtuacao> mapper= new MapperAreaAtuacaoVisualizarImplementacao();
		Optional<AreaAtuacao> AreaAtuacaoOptional = entidade.findById(id);

		if (AreaAtuacaoOptional.isPresent()) {
			return mapper.entidadeParaDto(AreaAtuacaoOptional.get());

		}
		
		throw new RecursoNaoEncontradoException("Area Atuacao nao encontrado");
	}

}
