package com.julia.api.catchup.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.api.catchup.dominio.AreaAtuacao;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoEditarDto;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoNovoDto;
import com.julia.api.catchup.dominio.dto.AreaAtuacaoVisualizarDto;
import com.julia.api.catchup.dominio.view.Usuario;
import com.julia.api.catchup.excessao.SemPermissaoEditarAreaAtuacaoException;
import com.julia.api.catchup.implementacao.EditarAreaAtuacaoImplementacao;
import com.julia.api.catchup.implementacao.SalvarAreaAtuacaoNovoImplementacao;
import com.julia.api.catchup.implementacao.VisualizarAreaAtuacaoImplementacao;
import com.julia.api.catchup.interfaces.EditarCatchupInterface;
import com.julia.api.catchup.interfaces.SalvarCatchupInterface;
import com.julia.api.catchup.repositorio.AreaAtuacaoRepositorio;

@Service
public class AreaAtuacaoService{

	@Autowired
	private AreaAtuacaoRepositorio areaAtuacaoRepositorio;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	public Boolean salvar(AreaAtuacaoNovoDto entidade,HttpSession session) {
		
		SalvarCatchupInterface<AreaAtuacaoNovoDto, Integer, AreaAtuacaoRepositorio> crud = new SalvarAreaAtuacaoNovoImplementacao();
		return crud.salvar(entidade, areaAtuacaoRepositorio);
	}



	
	public Boolean editar(AreaAtuacaoEditarDto entidade, HttpSession session) {
		if(verificaEdicao(entidade, session)== true) {
			EditarCatchupInterface<AreaAtuacaoEditarDto, Integer, AreaAtuacaoRepositorio> crud = new EditarAreaAtuacaoImplementacao();
			return crud.editar(entidade, areaAtuacaoRepositorio);
		}
		throw new SemPermissaoEditarAreaAtuacaoException("O usuario nao tem permissao de editar");
	}


	private Boolean verificaEdicao(AreaAtuacaoEditarDto entidade, HttpSession session) {
		String cpf = (String) session.getAttribute("cpf");
		Usuario usuario =(Usuario) usuarioService.pesquisaUsuario(cpf);
		Optional<AreaAtuacao> AreaAtuacaoOptional = areaAtuacaoRepositorio.findById(entidade.getId());
		if(AreaAtuacaoOptional.isPresent()) {
		
			if(usuario.getAuthorities().stream().filter(a-> a.getAuthority().contains("ADMINISTRADOR")).count()>0 ) {
				return true;
			}
		}
		
		return false;
	}
	
	public AreaAtuacaoVisualizarDto visualisarAreaAtuacaoId(Integer id) {
		VisualizarAreaAtuacaoImplementacao crud = new VisualizarAreaAtuacaoImplementacao();
		return crud.pesquisarId(id, areaAtuacaoRepositorio);
	}
	
	public List<AreaAtuacaoVisualizarDto> listarTodosAreaAtuacaos() {
		VisualizarAreaAtuacaoImplementacao crud = new VisualizarAreaAtuacaoImplementacao();
		return crud.listarTodos(areaAtuacaoRepositorio);
	}
	

	
	
}
