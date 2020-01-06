package com.julia.api.catchup.implementacao;

import java.util.Optional;

import com.julia.api.catchup.dominio.Funcionario;
import com.julia.api.catchup.dominio.dto.FuncionarioEditarDto;
import com.julia.api.catchup.interfaces.EditarCatchupInterface;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.repositorio.FuncionarioRepositorio;

public class EditarFuncionarioImplementacao implements EditarCatchupInterface<FuncionarioEditarDto, Integer, FuncionarioRepositorio> {

	@Override
	public Boolean editar(FuncionarioEditarDto entidade, FuncionarioRepositorio element) {
		MapperCatchupInterface<FuncionarioEditarDto, Funcionario> mapper= new MapperFuncionarioEditarImplementacao();
		Funcionario funcionario = mapper.dtoParaEntidade(entidade);
		Optional<Funcionario> funcionarioAntigoOptional = element.findById(funcionario.getId());
		if(funcionarioAntigoOptional.isPresent()) {
			Funcionario atualizado= funcionarioAntigoOptional.get();
			atualizado.setNome(funcionario.getNome());
			atualizado.setEmailRecuperacao(funcionario.getEmailRecuperacao());
			atualizado.setCpf(funcionario.getCpf());
			atualizado.setFilial(funcionario.getFilial());
			atualizado.setPerfil(funcionario.getPerfil());
			element.saveAndFlush(atualizado);
		}
		if (funcionario.getId() != null) {
			return true;
		}
		return false;
	}

}
