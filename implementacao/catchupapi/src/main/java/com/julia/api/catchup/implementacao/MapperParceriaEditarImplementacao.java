package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.julia.api.catchup.dominio.Parceria;
import com.julia.api.catchup.dominio.dto.ParceriaEditarDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;

public class MapperParceriaEditarImplementacao implements MapperCatchupInterface<ParceriaEditarDto,Parceria >{




	@Override
	public Parceria dtoParaEntidade(ParceriaEditarDto element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, Parceria.class);
	}



	@Override
	public ParceriaEditarDto entidadeParaDto(Parceria element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, ParceriaEditarDto.class);
	}



	@Override
	public List<ParceriaEditarDto> listaEntidadeParaDto(List<Parceria> element) {
		return element.stream().map(this::entidadeParaDto)
				.collect(Collectors.toList());
	}
}
