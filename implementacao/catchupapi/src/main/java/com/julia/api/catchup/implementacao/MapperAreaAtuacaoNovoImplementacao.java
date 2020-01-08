package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.julia.api.catchup.dominio.AreaAtuacao;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoNovoDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;

public class MapperAreaAtuacaoNovoImplementacao implements MapperCatchupInterface<AreaAtuacaoNovoDto,AreaAtuacao >{




	@Override
	public AreaAtuacao dtoParaEntidade(AreaAtuacaoNovoDto element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(AccessLevel.PRIVATE);
		
		return modelMapper.map(element, AreaAtuacao.class); 	
	}



	@Override
	public AreaAtuacaoNovoDto entidadeParaDto(AreaAtuacao element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, AreaAtuacaoNovoDto.class);
	}



	@Override
	public List<AreaAtuacaoNovoDto> listaEntidadeParaDto(List<AreaAtuacao> element) {
		return element.stream().map(this::entidadeParaDto)
				.collect(Collectors.toList());
	}
}
