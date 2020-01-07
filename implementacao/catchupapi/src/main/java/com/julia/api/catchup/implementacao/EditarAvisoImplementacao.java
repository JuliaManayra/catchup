package com.julia.api.catchup.implementacao;

import java.util.Optional;

import com.julia.api.catchup.dominio.Aviso;
import com.julia.api.catchup.dominio.dto.AvisoEditarDto;
import com.julia.api.catchup.interfaces.EditarCatchupInterface;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.repositorio.AvisoRepositorio;

public class EditarAvisoImplementacao implements EditarCatchupInterface<AvisoEditarDto, Integer, AvisoRepositorio> {

	@Override
	public Boolean editar(AvisoEditarDto entidade, AvisoRepositorio element) {
		MapperCatchupInterface<AvisoEditarDto, Aviso> mapper= new MapperAvisoEditarImplementacao();
		Aviso aviso = mapper.dtoParaEntidade(entidade);
		Optional<Aviso> avisoAntigoOptional = element.findById(aviso.getId());
		if(avisoAntigoOptional.isPresent()) {
			Aviso atualizado= avisoAntigoOptional.get();
			atualizado.setDescricao(aviso.getDescricao());
			atualizado.setImagem(aviso.getImagem());
			atualizado.setTitulo(aviso.getTitulo());
			element.saveAndFlush(atualizado);
		}
		if (aviso.getId() != null) {
			return true;
		}
		return false;
	}

}
