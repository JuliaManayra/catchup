package com.julia.api.catchup.implementacao;

import java.util.Optional;

import com.julia.api.catchup.dominio.AreaAtuacao;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoEditarDto;
import com.julia.api.catchup.interfaces.EditarCatchupInterface;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.repositorio.AreaAtuacaoRepositorio;

public class EditarAreaAtuacaoImplementacao implements EditarCatchupInterface<AreaAtuacaoEditarDto, Integer, AreaAtuacaoRepositorio> {

	@Override
	public Boolean editar(AreaAtuacaoEditarDto entidade, AreaAtuacaoRepositorio element) {
		MapperCatchupInterface<AreaAtuacaoEditarDto, AreaAtuacao> mapper= new MapperAreaAtuacaoEditarImplementacao();
		AreaAtuacao AreaAtuacao = mapper.dtoParaEntidade(entidade);
		Optional<AreaAtuacao> AreaAtuacaoAntigoOptional = element.findById(AreaAtuacao.getId());
		if(AreaAtuacaoAntigoOptional.isPresent()) {
			AreaAtuacao atualizado= AreaAtuacaoAntigoOptional.get();
			atualizado.setDescricao(AreaAtuacao.getDescricao());
			element.saveAndFlush(atualizado);
		}
		if (AreaAtuacao.getId() != null) {
			return true;
		}
		return false;
	}

}
