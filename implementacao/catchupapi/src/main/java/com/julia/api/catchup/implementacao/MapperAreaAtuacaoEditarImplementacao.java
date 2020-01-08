package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.julia.api.catchup.dominio.AreaAtuacao;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoEditarDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;

public class MapperAreaAtuacaoEditarImplementacao implements MapperCatchupInterface<AreaAtuacaoEditarDto,AreaAtuacao >{




	@Override
	public AreaAtuacao dtoParaEntidade(AreaAtuacaoEditarDto element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, AreaAtuacao.class);
	}



	@Override
	public AreaAtuacaoEditarDto entidadeParaDto(AreaAtuacao element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, AreaAtuacaoEditarDto.class);
	}



	@Override
	public List<AreaAtuacaoEditarDto> listaEntidadeParaDto(List<AreaAtuacao> element) {
		return element.stream().map(this::entidadeParaDto)
				.collect(Collectors.toList());
	}
}
