package com.julia.api.catchup.implementacao;

import com.julia.api.catchup.dominio.Parceria;
import com.julia.api.catchup.dominio.dto.ParceriaNovoDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.SalvarCatchupInterface;
import com.julia.api.catchup.repositorio.ParceriaRepositorio;

public class SalvarParceriaNovoImplementacao implements   SalvarCatchupInterface<ParceriaNovoDto, Integer,ParceriaRepositorio>{

	@Override
	public Boolean salvar(ParceriaNovoDto entidade, ParceriaRepositorio repositorio) {
		MapperCatchupInterface<ParceriaNovoDto, Parceria> mapper= new MapperParceriaNovoImplementacao();
		Parceria parceria = mapper.dtoParaEntidade(entidade);
		
		repositorio.saveAndFlush(parceria);

		if (parceria.getId() != null) {
			return true;
		}
		return false;
		
	}

}
