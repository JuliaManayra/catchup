package com.julia.api.catchup.implementacao;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.julia.api.catchup.dominio.Funcionario;
import com.julia.api.catchup.dominio.dto.FuncionarioEditarSenhaDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;

public class MapperFuncionarioEditarSenhaImplementacao implements MapperCatchupInterface<FuncionarioEditarSenhaDto,Funcionario >{




	@Override
	public Funcionario dtoParaEntidade(FuncionarioEditarSenhaDto element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, Funcionario.class);
	}



	@Override
	public FuncionarioEditarSenhaDto entidadeParaDto(Funcionario element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, FuncionarioEditarSenhaDto.class);
	}



	@Override
	public List<FuncionarioEditarSenhaDto> listaEntidadeParaDto(List<Funcionario> element) {
		return new ArrayList<>();
	}
}
