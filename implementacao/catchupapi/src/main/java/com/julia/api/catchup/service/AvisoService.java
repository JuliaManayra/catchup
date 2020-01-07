package com.julia.api.catchup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.api.catchup.dominio.dto.AvisoEditarDto;
import com.julia.api.catchup.dominio.dto.AvisoNovoDto;
import com.julia.api.catchup.dominio.dto.AvisoVisualizarDto;
import com.julia.api.catchup.implementacao.EditarAvisoImplementacao;
import com.julia.api.catchup.implementacao.SalvarAvisoNovoImplementacao;
import com.julia.api.catchup.implementacao.VisualizarAvisoImplementacao;
import com.julia.api.catchup.interfaces.EditarCatchupInterface;
import com.julia.api.catchup.interfaces.SalvarCatchupInterface;
import com.julia.api.catchup.repositorio.AvisoRepositorio;

@Service
public class AvisoService{

	@Autowired
	private AvisoRepositorio avisoRepositorio;

	
	
	public Boolean salvar(AvisoNovoDto entidade) {
		SalvarCatchupInterface<AvisoNovoDto, Integer, AvisoRepositorio> crud = new SalvarAvisoNovoImplementacao();
		return crud.salvar(entidade, avisoRepositorio);
	}

	
	public Boolean editar(AvisoEditarDto entidade) {
		EditarCatchupInterface<AvisoEditarDto, Integer, AvisoRepositorio> crud = new EditarAvisoImplementacao();
		return crud.editar(entidade, avisoRepositorio);
	}
	
	public AvisoVisualizarDto visualisarAvisoId(Integer id) {
		VisualizarAvisoImplementacao crud = new VisualizarAvisoImplementacao();
		return crud.pesquisarId(id, avisoRepositorio);
	}
	
	public List<AvisoVisualizarDto> listarTodosAvisos() {
		VisualizarAvisoImplementacao crud = new VisualizarAvisoImplementacao();
		return crud.listarTodos(avisoRepositorio);
	}
	

}
