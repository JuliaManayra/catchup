package com.julia.api.catchup.implementacao;

import java.util.List;

import com.julia.api.catchup.dominio.Aviso;
import com.julia.api.catchup.dominio.dto.AvisoVisualizarDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.VisualizarPorParametroCatchupInterface;
import com.julia.api.catchup.repositorio.AvisoRepositorio;

public class VisualizarAvisoPorParametroImplementacao implements VisualizarPorParametroCatchupInterface<AvisoVisualizarDto, String, AvisoRepositorio> {

	

	@Override
	public List<AvisoVisualizarDto> listarTodos(String parametro, AvisoRepositorio element) {
		MapperCatchupInterface<AvisoVisualizarDto, Aviso> mapper= new MapperAvisoVisualizarImplementacao();
		return mapper.listaEntidadeParaDto(element.findByFuncionarioCpf(parametro));
		
	}

	

}
