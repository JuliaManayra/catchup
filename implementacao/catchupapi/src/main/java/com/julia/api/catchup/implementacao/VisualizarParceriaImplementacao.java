package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.Optional;

import com.julia.api.catchup.dominio.Parceria;
import com.julia.api.catchup.dominio.dto.ParceriaVisualizarDto;
import com.julia.api.catchup.excessao.RecursoNaoEncontradoException;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.VisualizarCatchupInterface;
import com.julia.api.catchup.repositorio.ParceriaRepositorio;

public class VisualizarParceriaImplementacao implements VisualizarCatchupInterface<ParceriaVisualizarDto, Integer, ParceriaRepositorio> {

	

	@Override
	public List<ParceriaVisualizarDto> listarTodos(ParceriaRepositorio element) {
		MapperCatchupInterface<ParceriaVisualizarDto, Parceria> mapper= new MapperParceriaVisualizarImplementacao();
		return mapper.listaEntidadeParaDto(element.findAll());
	}

	@Override
	public ParceriaVisualizarDto pesquisarId(Integer id,ParceriaRepositorio entidade) {
		MapperCatchupInterface<ParceriaVisualizarDto, Parceria> mapper= new MapperParceriaVisualizarImplementacao();
		Optional<Parceria> parceriaOptional = entidade.findById(id);

		if (parceriaOptional.isPresent()) {
			return mapper.entidadeParaDto(parceriaOptional.get());

		}
		
		throw new RecursoNaoEncontradoException("Parceria nao encontrado");
	}

}
