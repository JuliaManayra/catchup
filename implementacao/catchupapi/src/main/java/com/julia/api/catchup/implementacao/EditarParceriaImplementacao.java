package com.julia.api.catchup.implementacao;

import java.util.Optional;

import com.julia.api.catchup.dominio.Parceria;
import com.julia.api.catchup.dominio.dto.ParceriaEditarDto;
import com.julia.api.catchup.interfaces.EditarCatchupInterface;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.repositorio.ParceriaRepositorio;

public class EditarParceriaImplementacao implements EditarCatchupInterface<ParceriaEditarDto, Integer, ParceriaRepositorio> {

	@Override
	public Boolean editar(ParceriaEditarDto entidade, ParceriaRepositorio element) {
		MapperCatchupInterface<ParceriaEditarDto, Parceria> mapper= new MapperParceriaEditarImplementacao();
		Parceria parceria = mapper.dtoParaEntidade(entidade);
		Optional<Parceria> ParceriaAntigoOptional = element.findById(parceria.getId());
		if(ParceriaAntigoOptional.isPresent()) {
			Parceria atualizado= ParceriaAntigoOptional.get();
			atualizado.setDescricao(parceria.getDescricao());
			atualizado.setImagem(parceria.getImagem());
			atualizado.setTitulo(parceria.getTitulo());
			
			atualizado.setAreaAtuacao(parceria.getAreaAtuacao());
			atualizado.setEndereco(parceria.getEndereco());
			atualizado.setTelefone(parceria.getTelefone());
			
			
			element.saveAndFlush(atualizado);
		}
		if (parceria.getId() != null) {
			return true;
		}
		return false;
	}

}
