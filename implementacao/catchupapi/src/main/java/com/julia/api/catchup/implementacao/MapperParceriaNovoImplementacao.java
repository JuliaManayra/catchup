package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.julia.api.catchup.dominio.Parceria;
import com.julia.api.catchup.dominio.dto.ParceriaNovoDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;

public class MapperParceriaNovoImplementacao implements MapperCatchupInterface<ParceriaNovoDto,Parceria >{




	@Override
	public Parceria dtoParaEntidade(ParceriaNovoDto element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(AccessLevel.PRIVATE);
		
		return modelMapper.map(element, Parceria.class); 	
	}



	@Override
	public ParceriaNovoDto entidadeParaDto(Parceria element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, ParceriaNovoDto.class);
	}



	@Override
	public List<ParceriaNovoDto> listaEntidadeParaDto(List<Parceria> element) {
		return element.stream().map(this::entidadeParaDto)
				.collect(Collectors.toList());
	}
}
