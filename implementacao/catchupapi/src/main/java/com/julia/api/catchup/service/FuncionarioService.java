package com.julia.api.catchup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.api.catchup.dominio.dto.FuncionarioEditarDto;
import com.julia.api.catchup.dominio.dto.FuncionarioEditarSenhaDto;
import com.julia.api.catchup.dominio.dto.FuncionarioNovoDto;
import com.julia.api.catchup.dominio.dto.FuncionarioVisualizarDto;
import com.julia.api.catchup.dominio.view.Usuario;
import com.julia.api.catchup.implementacao.CrudImplementacaoFuncionarioNovo;
import com.julia.api.catchup.implementacao.EditarFuncionarioImplementacao;
import com.julia.api.catchup.implementacao.EditarSenhaFuncionarioImplementacao;
import com.julia.api.catchup.implementacao.VisualizarFuncionarioImplementacao;
import com.julia.api.catchup.interfaces.EditarCatchupInterface;
import com.julia.api.catchup.interfaces.SalvarCatchupInterface;
import com.julia.api.catchup.repositorio.FuncionarioRepositorio;

@Service
public class FuncionarioService{

	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;


	@Autowired
	private UsuarioService usuarioService;
	
	
	
	public Boolean salvar(FuncionarioNovoDto entidade) {
		SalvarCatchupInterface<FuncionarioNovoDto, Integer, FuncionarioRepositorio> crud = new CrudImplementacaoFuncionarioNovo();
		return crud.salvar(entidade, funcionarioRepositorio);
	}

	
	public Boolean editar(FuncionarioEditarDto entidade) {
		EditarCatchupInterface<FuncionarioEditarDto, Integer, FuncionarioRepositorio> crud = new EditarFuncionarioImplementacao();
		return crud.editar(entidade, funcionarioRepositorio);
	}
	
	public FuncionarioVisualizarDto visualisarFuncionarioId(Integer id) {
		VisualizarFuncionarioImplementacao crud = new VisualizarFuncionarioImplementacao();
		return crud.pesquisarId(id, funcionarioRepositorio);
	}
	
	public List<FuncionarioVisualizarDto> listarTodosFuncionarios() {
		VisualizarFuncionarioImplementacao crud = new VisualizarFuncionarioImplementacao();
		return crud.listarTodos(funcionarioRepositorio);
	}
	
	public Boolean alterarSenha(FuncionarioEditarSenhaDto entidade, String cpf) {
		Usuario funcionario = (Usuario) usuarioService.pesquisaUsuario(cpf);
		entidade.setId(funcionario.getId());
		EditarCatchupInterface<FuncionarioEditarSenhaDto, Integer, FuncionarioRepositorio> crud = new EditarSenhaFuncionarioImplementacao();
		return crud.editar(entidade, funcionarioRepositorio);
	}

}
