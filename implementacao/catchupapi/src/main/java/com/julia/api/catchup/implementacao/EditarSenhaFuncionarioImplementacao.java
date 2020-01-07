package com.julia.api.catchup.implementacao;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.julia.api.catchup.dominio.Funcionario;
import com.julia.api.catchup.dominio.dto.FuncionarioEditarSenhaDto;
import com.julia.api.catchup.interfaces.EditarCatchupInterface;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.repositorio.FuncionarioRepositorio;

public class EditarSenhaFuncionarioImplementacao implements EditarCatchupInterface<FuncionarioEditarSenhaDto, Integer, FuncionarioRepositorio> {

	@Override
	public Boolean editar(FuncionarioEditarSenhaDto entidade, FuncionarioRepositorio element) {
		
		
		MapperCatchupInterface<FuncionarioEditarSenhaDto, Funcionario> mapper= new MapperFuncionarioEditarSenhaImplementacao();
		Funcionario funcionario = mapper.dtoParaEntidade(entidade);
		Optional<Funcionario> funcionarioAntigoOptional = element.findById(funcionario.getId());
		if(funcionarioAntigoOptional.isPresent()) {
			Funcionario atualizado= funcionarioAntigoOptional.get();
			String encript = BCrypt.hashpw(funcionario.getSenha(), BCrypt.gensalt());
			funcionario.setSenha(encript);
			funcionario.setSenhaConfirmacao(encript);
			atualizado.setSenha(funcionario.getSenha());
			atualizado.setSenhaConfirmacao(funcionario.getSenhaConfirmacao());
			element.saveAndFlush(atualizado);
		}
		if (funcionario.getId() != null) {
			return true;
		}
		return false;
	}

}
