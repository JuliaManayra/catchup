package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.Optional;

import com.julia.api.catchup.dominio.Aviso;
import com.julia.api.catchup.dominio.dto.AvisoVisualizarDto;
import com.julia.api.catchup.excessao.RecursoNaoEncontradoException;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.VisualizarCatchupInterface;
import com.julia.api.catchup.repositorio.AvisoRepositorio;

public class VisualizarAvisoImplementacao implements VisualizarCatchupInterface<AvisoVisualizarDto, Integer, AvisoRepositorio> {

	

	@Override
	public List<AvisoVisualizarDto> listarTodos(AvisoRepositorio element) {
		MapperCatchupInterface<AvisoVisualizarDto, Aviso> mapper= new MapperAvisoVisualizarImplementacao();
		return mapper.listaEntidadeParaDto(element.findAll());
	}

	@Override
	public AvisoVisualizarDto pesquisarId(Integer id,AvisoRepositorio entidade) {
		MapperCatchupInterface<AvisoVisualizarDto, Aviso> mapper= new MapperAvisoVisualizarImplementacao();
		Optional<Aviso> AvisoOptional = entidade.findById(id);

		if (AvisoOptional.isPresent()) {
			return mapper.entidadeParaDto(AvisoOptional.get());

		}
		
		throw new RecursoNaoEncontradoException("Aviso nao encontrado");
	}

}
