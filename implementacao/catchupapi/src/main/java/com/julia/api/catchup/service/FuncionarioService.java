package com.julia.api.catchup.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.api.catchup.dominio.Funcionario;
import com.julia.api.catchup.dominio.dto.FuncionarioNovoDto;
import com.julia.api.catchup.dominio.dto.FuncionarioVisualizarDto;
import com.julia.api.catchup.excessao.RecursoNaoEncontradoException;
import com.julia.api.catchup.repositorio.FuncionarioRepositorio;


@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;
	
	
	
	@Autowired
	private  CriptografiaService criptografiaService;

	
	public FuncionarioVisualizarDto visualisarFuncionarioId(Integer id) {
		Optional<Funcionario> funcionarioOptional = funcionarioRepositorio.findById(id);
		
		if(funcionarioOptional.isPresent()) {
			return funcionarioParaVisualizarFuncionarioDto(funcionarioOptional.get());
			
		}
		
		throw new RecursoNaoEncontradoException("Usuario nao encontrado");
	}




	private FuncionarioVisualizarDto funcionarioParaVisualizarFuncionarioDto(Funcionario funcionario) {
		ModelMapper modelMapper = new ModelMapper();
					modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setFieldMatchingEnabled(true);

		return modelMapper.map(funcionario,FuncionarioVisualizarDto.class);
	}
	
	
	private List<FuncionarioVisualizarDto> listaFuncionarioParaVisualizarFuncionarioDto(List<Funcionario> listaFuncionario){
		return listaFuncionario.stream()
		          .map(this::funcionarioParaVisualizarFuncionarioDto)
		          .collect(Collectors.toList());
	}
	

	private Funcionario funcionarioNovoDtoParaFuncionario(FuncionarioNovoDto dto) {
		ModelMapper modelMapper = new ModelMapper();
					modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
					.setFieldAccessLevel(AccessLevel.PRIVATE)
					.setFieldMatchingEnabled(true);

		return modelMapper.map(dto,Funcionario.class);
	}
	
	public Boolean salvarNovoFuncionario(FuncionarioNovoDto funcionarioNovoDto) {
		Funcionario funcionario = trataDadosFuncionario(funcionarioNovoDto);
		funcionarioRepositorio.saveAndFlush(funcionario);
		
		if(funcionario.getId()!=null) {
			return true;
		}
		return false;
		
	}
	
	
	private Funcionario trataDadosFuncionario(FuncionarioNovoDto funcionarioNovoDto) {
		
		funcionarioNovoDto.setSenha(criptografiaService.encriptar(funcionarioNovoDto.getSenha()));
		funcionarioNovoDto.setSenhaConfirmacao(criptografiaService.encriptar(funcionarioNovoDto.getSenhaConfirmacao()));
		
		return funcionarioNovoDtoParaFuncionario(funcionarioNovoDto);
	}
}
