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
import com.julia.api.catchup.dominio.view.Usuario;
import com.julia.api.catchup.excessao.SemPermissaoEditarAvisoException;
import com.julia.api.catchup.implementacao.EditarAvisoImplementacao;
import com.julia.api.catchup.implementacao.SalvarAvisoNovoImplementacao;
import com.julia.api.catchup.implementacao.VisualizarAvisoImplementacao;
import com.julia.api.catchup.implementacao.VisualizarAvisoPorParametroImplementacao;
import com.julia.api.catchup.interfaces.EditarCatchupInterface;
import com.julia.api.catchup.interfaces.SalvarCatchupInterface;
import com.julia.api.catchup.repositorio.AvisoRepositorio;

@Service
public class AvisoService{

	@Autowired
	private AvisoRepositorio avisoRepositorio;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	public Boolean salvar(AvisoNovoDto entidade,HttpSession session) {
		
		SalvarCatchupInterface<AvisoNovoDto, Integer, AvisoRepositorio> crud = new SalvarAvisoNovoImplementacao();
		return crud.salvar(setUsuarioSessao(entidade, session), avisoRepositorio);
	}


	private AvisoNovoDto setUsuarioSessao(AvisoNovoDto entidade, HttpSession session) {
		String cpf = (String) session.getAttribute("cpf");
		Usuario usuario =(Usuario) usuarioService.pesquisaUsuario(cpf);
		entidade.setIdFuncionario(usuario.getId());
		return entidade;
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
		Usuario usuario =(Usuario) usuarioService.pesquisaUsuario(cpf);
		Optional<Aviso> avisoOptional = avisoRepositorio.findById(entidade.getId());
		if(avisoOptional.isPresent()) {
			Aviso aviso = avisoOptional.get();
			if(aviso.getFuncionario()!=null && aviso.getFuncionario().getCpf().equals(cpf) || usuario.getAuthorities().stream().filter(a-> a.getAuthority().contains("ADMINISTRADOR")).count()>0 ) {
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
	

	public List<AvisoVisualizarDto> listarTodosMeusAvisos(HttpSession session) {
		String cpf = (String) session.getAttribute("cpf");
		VisualizarAvisoPorParametroImplementacao crud = new VisualizarAvisoPorParametroImplementacao();
		return crud.listarTodos(cpf,avisoRepositorio);
	}
	
}
