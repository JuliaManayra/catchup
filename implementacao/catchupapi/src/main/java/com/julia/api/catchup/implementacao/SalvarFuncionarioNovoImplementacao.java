package com.julia.api.catchup.implementacao;

import com.julia.api.catchup.dominio.Funcionario;
import com.julia.api.catchup.dominio.dto.FuncionarioNovoDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.SalvarCatchupInterface;
import com.julia.api.catchup.repositorio.FuncionarioRepositorio;

public class SalvarFuncionarioNovoImplementacao implements   SalvarCatchupInterface<FuncionarioNovoDto, Integer,FuncionarioRepositorio>{

	@Override
	public Boolean salvar(FuncionarioNovoDto entidade, FuncionarioRepositorio repositorio) {
		MapperCatchupInterface<FuncionarioNovoDto, Funcionario> mapper= new MapperFuncionarioNovoImplementacao();
		Funcionario funcionario = mapper.dtoParaEntidade(entidade);
		repositorio.saveAndFlush(funcionario);

		if (funcionario.getId() != null) {
			return true;
		}
		return false;
		
	}

}
