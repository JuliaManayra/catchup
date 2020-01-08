package com.julia.api.catchup.implementacao;

import com.julia.api.catchup.dominio.AreaAtuacao;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoNovoDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.SalvarCatchupInterface;
import com.julia.api.catchup.repositorio.AreaAtuacaoRepositorio;

public class SalvarAreaAtuacaoNovoImplementacao implements   SalvarCatchupInterface<AreaAtuacaoNovoDto, Integer,AreaAtuacaoRepositorio>{

	@Override
	public Boolean salvar(AreaAtuacaoNovoDto entidade, AreaAtuacaoRepositorio repositorio) {
		MapperCatchupInterface<AreaAtuacaoNovoDto, AreaAtuacao> mapper= new MapperAreaAtuacaoNovoImplementacao();
		AreaAtuacao AreaAtuacao = mapper.dtoParaEntidade(entidade);
		repositorio.saveAndFlush(AreaAtuacao);

		if (AreaAtuacao.getId() != null) {
			return true;
		}
		return false;
		
	}

}
