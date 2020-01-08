package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.Optional;

import com.julia.api.catchup.dominio.AreaAtuacao;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoParceriaVisualizarDto;
import com.julia.api.catchup.excessao.RecursoNaoEncontradoException;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.VisualizarCatchupInterface;
import com.julia.api.catchup.repositorio.AreaAtuacaoRepositorio;

public class VisualizarAreaAtuacaoParceriaImplementacao implements VisualizarCatchupInterface<AreaAtuacaoParceriaVisualizarDto, Integer, AreaAtuacaoRepositorio> {

	

	@Override
	public List<AreaAtuacaoParceriaVisualizarDto> listarTodos(AreaAtuacaoRepositorio element) {
		MapperCatchupInterface<AreaAtuacaoParceriaVisualizarDto, AreaAtuacao> mapper= new MapperAreaAtuacaoParceriaVisualizarImplementacao();
		return mapper.listaEntidadeParaDto(element.findAll());
	}

	@Override
	public AreaAtuacaoParceriaVisualizarDto pesquisarId(Integer id,AreaAtuacaoRepositorio entidade) {
		MapperCatchupInterface<AreaAtuacaoParceriaVisualizarDto, AreaAtuacao> mapper= new MapperAreaAtuacaoParceriaVisualizarImplementacao();
		Optional<AreaAtuacao> AreaAtuacaoOptional = entidade.findById(id);

		if (AreaAtuacaoOptional.isPresent()) {
			return mapper.entidadeParaDto(AreaAtuacaoOptional.get());

		}
		
		throw new RecursoNaoEncontradoException("Area Atuacao nao encontrado");
	}

}
