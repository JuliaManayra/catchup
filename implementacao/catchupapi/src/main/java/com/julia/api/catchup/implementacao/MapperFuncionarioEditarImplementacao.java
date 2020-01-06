package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.julia.api.catchup.dominio.Funcionario;
import com.julia.api.catchup.dominio.dto.FuncionarioEditarDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;

public class MapperFuncionarioEditarImplementacao implements MapperCatchupInterface<FuncionarioEditarDto,Funcionario >{




	@Override
	public Funcionario dtoParaEntidade(FuncionarioEditarDto element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, Funcionario.class);
	}



	@Override
	public FuncionarioEditarDto entidadeParaDto(Funcionario element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, FuncionarioEditarDto.class);
	}



	@Override
	public List<FuncionarioEditarDto> listaEntidadeParaDto(List<Funcionario> element) {
		return element.stream().map(this::entidadeParaDto)
				.collect(Collectors.toList());
	}
}
