package com.julia.api.catchup.implementacao;

import java.util.Date;

import com.julia.api.catchup.dominio.Aviso;
import com.julia.api.catchup.dominio.dto.AvisoNovoDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.SalvarCatchupInterface;
import com.julia.api.catchup.repositorio.AvisoRepositorio;

public class SalvarAvisoNovoImplementacao implements   SalvarCatchupInterface<AvisoNovoDto, Integer,AvisoRepositorio>{

	@Override
	public Boolean salvar(AvisoNovoDto entidade, AvisoRepositorio repositorio) {
		MapperCatchupInterface<AvisoNovoDto, Aviso> mapper= new MapperAvisoNovoImplementacao();
		Aviso aviso = mapper.dtoParaEntidade(entidade);
		aviso.setData(new Date());
		repositorio.saveAndFlush(aviso);

		if (aviso.getId() != null) {
			return true;
		}
		return false;
		
	}

}
