package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.Optional;

import com.julia.api.catchup.dominio.Funcionario;
import com.julia.api.catchup.dominio.dto.FuncionarioVisualizarDto;
import com.julia.api.catchup.excessao.RecursoNaoEncontradoException;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.VisualizarCatchupInterface;
import com.julia.api.catchup.repositorio.FuncionarioRepositorio;

public class VisualizarFuncionarioImplementacao implements VisualizarCatchupInterface<FuncionarioVisualizarDto, Integer, FuncionarioRepositorio> {

	

	@Override
	public List<FuncionarioVisualizarDto> listarTodos(FuncionarioRepositorio element) {
		MapperCatchupInterface<FuncionarioVisualizarDto, Funcionario> mapper= new MapperFuncionarioVisualizarImplementacao();
		return mapper.listaEntidadeParaDto(element.findAll());
	}

	@Override
	public FuncionarioVisualizarDto pesquisarId(Integer id,FuncionarioRepositorio entidade) {
		MapperCatchupInterface<FuncionarioVisualizarDto, Funcionario> mapper= new MapperFuncionarioVisualizarImplementacao();
		Optional<Funcionario> funcionarioOptional = entidade.findById(id);

		if (funcionarioOptional.isPresent()) {
			return mapper.entidadeParaDto(funcionarioOptional.get());

		}
		
		throw new RecursoNaoEncontradoException("Usuario nao encontrado");
	}

}
