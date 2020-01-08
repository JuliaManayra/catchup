package com.julia.api.catchup.implementacao;

import java.util.List;

import com.julia.api.catchup.dominio.Parceria;
import com.julia.api.catchup.dominio.dto.ParceriaVisualizarDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.VisualizarPorParametroCatchupInterface;
import com.julia.api.catchup.repositorio.ParceriaRepositorio;

public class VisualizarParceriaPorParametroImplementacao implements VisualizarPorParametroCatchupInterface<ParceriaVisualizarDto, String, ParceriaRepositorio> {

	

	@Override
	public List<ParceriaVisualizarDto> listarTodos(String parametro, ParceriaRepositorio element) {
		MapperCatchupInterface<ParceriaVisualizarDto, Parceria> mapper= new MapperParceriaVisualizarImplementacao();
		return mapper.listaEntidadeParaDto(element.findByFuncionarioCpf(parametro));
		
	}

	

}
