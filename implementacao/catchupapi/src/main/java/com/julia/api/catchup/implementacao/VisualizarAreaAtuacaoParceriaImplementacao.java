package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.Optional;

import com.julia.api.catchup.dominio.AreaAtuacao;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoVisualizarParceriaDto;
import com.julia.api.catchup.excessao.RecursoNaoEncontradoException;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.VisualizarCatchupInterface;
import com.julia.api.catchup.repositorio.AreaAtuacaoRepositorio;

public class VisualizarAreaAtuacaoParceriaImplementacao implements VisualizarCatchupInterface<AreaAtuacaoVisualizarParceriaDto, Integer, AreaAtuacaoRepositorio> {

	

	@Override
	public List<AreaAtuacaoVisualizarParceriaDto> listarTodos(AreaAtuacaoRepositorio element) {
		MapperCatchupInterface<AreaAtuacaoVisualizarParceriaDto, AreaAtuacao> mapper= new MapperAreaAtuacaoParceriaVisualizarImplementacao();
		return mapper.listaEntidadeParaDto(element.findAll());
	}

	@Override
	public AreaAtuacaoVisualizarParceriaDto pesquisarId(Integer id,AreaAtuacaoRepositorio entidade) {
		MapperCatchupInterface<AreaAtuacaoVisualizarParceriaDto, AreaAtuacao> mapper= new MapperAreaAtuacaoParceriaVisualizarImplementacao();
		Optional<AreaAtuacao> AreaAtuacaoOptional = entidade.findById(id);

		if (AreaAtuacaoOptional.isPresent()) {
			return mapper.entidadeParaDto(AreaAtuacaoOptional.get());

		}
		
		throw new RecursoNaoEncontradoException("Area Atuacao nao encontrado");
	}

}
