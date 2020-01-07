package com.julia.api.catchup.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.api.catchup.dominio.Aviso;
import com.julia.api.catchup.dominio.dto.AvisoEditarDto;
import com.julia.api.catchup.dominio.dto.AvisoNovoDto;
import com.julia.api.catchup.dominio.dto.AvisoVisualizarDto;
import com.julia.api.catchup.excessao.SemPermissaoEditarAvisoException;
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

	
	public Boolean editar(AvisoEditarDto entidade, HttpSession session) {
		if(verificaEdicao(entidade, session)== true) {
			EditarCatchupInterface<AvisoEditarDto, Integer, AvisoRepositorio> crud = new EditarAvisoImplementacao();
			return crud.editar(entidade, avisoRepositorio);
		}
		throw new SemPermissaoEditarAvisoException("O usuario nao tem permissao de editar");
	}


	private Boolean verificaEdicao(AvisoEditarDto entidade, HttpSession session) {
		String cpf = (String) session.getAttribute("cpf");
		Optional<Aviso> avisoOptional = avisoRepositorio.findById(entidade.getId());
		if(avisoOptional.isPresent()) {
			Aviso aviso = avisoOptional.get();
			if(aviso.getFuncionarioCriador()!=null && aviso.getFuncionarioCriador().getCpf().equals(cpf)) {
				return true;
			}
		}
		
		return false;
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
