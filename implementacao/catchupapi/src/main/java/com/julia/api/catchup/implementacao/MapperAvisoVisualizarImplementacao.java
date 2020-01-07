package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.julia.api.catchup.dominio.Aviso;
import com.julia.api.catchup.dominio.dto.AvisoVisualizarDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;

public class MapperAvisoVisualizarImplementacao implements MapperCatchupInterface<AvisoVisualizarDto,Aviso >{


	@Override
	public Aviso dtoParaEntidade(AvisoVisualizarDto  element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, Aviso.class);
	}

	@Override
	public AvisoVisualizarDto entidadeParaDto(Aviso  element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, AvisoVisualizarDto.class);
	}

	@Override
	public List<AvisoVisualizarDto> listaEntidadeParaDto(List<Aviso> element) {
		return element.stream().map(this::entidadeParaDto)
				.collect(Collectors.toList());
	}

	
}
