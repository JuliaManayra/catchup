package com.julia.api.catchup.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.api.catchup.dominio.Parceria;
import com.julia.api.catchup.dominio.dto.ParceriaEditarDto;
import com.julia.api.catchup.dominio.dto.ParceriaNovoDto;
import com.julia.api.catchup.dominio.dto.ParceriaVisualizarDto;
import com.julia.api.catchup.dominio.view.Usuario;
import com.julia.api.catchup.excessao.SemPermissaoEditarParceriaException;
import com.julia.api.catchup.implementacao.EditarParceriaImplementacao;
import com.julia.api.catchup.implementacao.SalvarParceriaNovoImplementacao;
import com.julia.api.catchup.implementacao.VisualizarParceriaImplementacao;
import com.julia.api.catchup.implementacao.VisualizarParceriaPorParametroImplementacao;
import com.julia.api.catchup.interfaces.EditarCatchupInterface;
import com.julia.api.catchup.interfaces.SalvarCatchupInterface;
import com.julia.api.catchup.repositorio.ParceriaRepositorio;

@Service
public class ParceriaService{

	@Autowired
	private ParceriaRepositorio parceriaRepositorio;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	public Boolean salvar(ParceriaNovoDto entidade,HttpSession session) {
		
		SalvarCatchupInterface<ParceriaNovoDto, Integer, ParceriaRepositorio> crud = new SalvarParceriaNovoImplementacao();
		return crud.salvar(setUsuarioSessao(entidade, session), parceriaRepositorio);
	}


	private ParceriaNovoDto setUsuarioSessao(ParceriaNovoDto entidade, HttpSession session) {
		String cpf = (String) session.getAttribute("cpf");
		Usuario usuario =(Usuario) usuarioService.pesquisaUsuario(cpf);
		entidade.setIdFuncionario(usuario.getId());
		return entidade;
	}

	
	public Boolean editar(ParceriaEditarDto entidade, HttpSession session) {
		if(verificaEdicao(entidade, session)== true) {
			EditarCatchupInterface<ParceriaEditarDto, Integer, ParceriaRepositorio> crud = new EditarParceriaImplementacao();
			return crud.editar(entidade, parceriaRepositorio);
		}
		throw new SemPermissaoEditarParceriaException("O usuario nao tem permissao de editar");
	}


	private Boolean verificaEdicao(ParceriaEditarDto entidade, HttpSession session) {
		String cpf = (String) session.getAttribute("cpf");
		Usuario usuario =(Usuario) usuarioService.pesquisaUsuario(cpf);
		Optional<Parceria> ParceriaOptional = parceriaRepositorio.findById(entidade.getId());
		if(ParceriaOptional.isPresent()) {
			Parceria Parceria = ParceriaOptional.get();
			if(Parceria.getFuncionario()!=null && Parceria.getFuncionario().getCpf().equals(cpf)  || usuario.getAuthorities().stream().filter(a-> a.getAuthority().contains("ADMINISTRADOR")).count()>0) {
				return true;
			}
		}
		
		return false;
	}
	
	public ParceriaVisualizarDto visualisarParceriaId(Integer id) {
		VisualizarParceriaImplementacao crud = new VisualizarParceriaImplementacao();
		return crud.pesquisarId(id, parceriaRepositorio);
	}
	
	public List<ParceriaVisualizarDto> listarTodosParcerias() {
		VisualizarParceriaImplementacao crud = new VisualizarParceriaImplementacao();
		return crud.listarTodos(parceriaRepositorio);
	}
	

	public List<ParceriaVisualizarDto> listarTodosMeusParcerias(HttpSession session) {
		String cpf = (String) session.getAttribute("cpf");
		VisualizarParceriaPorParametroImplementacao crud = new VisualizarParceriaPorParametroImplementacao();
		return crud.listarTodos(cpf,parceriaRepositorio);
	}
	
}
