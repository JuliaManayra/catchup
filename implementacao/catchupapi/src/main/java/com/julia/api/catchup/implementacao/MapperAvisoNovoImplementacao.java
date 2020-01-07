package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.julia.api.catchup.dominio.Aviso;
import com.julia.api.catchup.dominio.dto.AvisoNovoDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;

public class MapperAvisoNovoImplementacao implements MapperCatchupInterface<AvisoNovoDto,Aviso >{




	@Override
	public Aviso dtoParaEntidade(AvisoNovoDto element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(AccessLevel.PRIVATE);
		
		return modelMapper.map(element, Aviso.class); 	
	}



	@Override
	public AvisoNovoDto entidadeParaDto(Aviso element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, AvisoNovoDto.class);
	}



	@Override
	public List<AvisoNovoDto> listaEntidadeParaDto(List<Aviso> element) {
		return element.stream().map(this::entidadeParaDto)
				.collect(Collectors.toList());
	}
}
