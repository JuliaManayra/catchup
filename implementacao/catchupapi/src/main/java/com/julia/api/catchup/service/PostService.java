package com.julia.api.catchup.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.julia.api.catchup.dominio.Funcionario;
import com.julia.api.catchup.dominio.Post;
import com.julia.api.catchup.dominio.dto.PostEditarDto;
import com.julia.api.catchup.dominio.dto.PostNovoDto;
import com.julia.api.catchup.dominio.dto.PostVisualizarDto;
import com.julia.api.catchup.dominio.view.Usuario;
import com.julia.api.catchup.excessao.RecursoNaoEncontradoException;
import com.julia.api.catchup.excessao.SemPermissaoEditarPostException;
import com.julia.api.catchup.implementacao.EditarPostImplementacao;
import com.julia.api.catchup.implementacao.SalvarPostNovoImplementacao;
import com.julia.api.catchup.implementacao.VisualizarPostImplementacao;
import com.julia.api.catchup.implementacao.VisualizarPostPorParametroImplementacao;
import com.julia.api.catchup.interfaces.EditarCatchupInterface;
import com.julia.api.catchup.interfaces.SalvarCatchupInterface;
import com.julia.api.catchup.repositorio.PostRepositorio;

@Service
public class PostService{

	@Autowired
	private PostRepositorio postRepositorio;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	public Boolean salvar(PostNovoDto entidade,HttpSession session) {
		
		SalvarCatchupInterface<PostNovoDto, Integer, PostRepositorio> crud = new SalvarPostNovoImplementacao();
		return crud.salvar(setUsuarioSessao(entidade, session), postRepositorio);
	}


	private PostNovoDto setUsuarioSessao(PostNovoDto entidade, HttpSession session) {
		String cpf = (String) session.getAttribute("cpf");
		Usuario usuario =(Usuario) usuarioService.pesquisaUsuario(cpf);
		entidade.setIdFuncionario(usuario.getId());
		return entidade;
	}

	
	public Boolean editar(PostEditarDto entidade, HttpSession session) {
		if(verificaEdicao(entidade, session)== true) {
			EditarCatchupInterface<PostEditarDto, Integer, PostRepositorio> crud = new EditarPostImplementacao();
			return crud.editar(entidade, postRepositorio);
		}
		throw new SemPermissaoEditarPostException("O usuario nao tem permissao de editar");
	}


	private Boolean verificaEdicao(PostEditarDto entidade, HttpSession session) {
		String cpf = (String) session.getAttribute("cpf");
		Optional<Post> postOptional = postRepositorio.findById(entidade.getId());
		if(postOptional.isPresent()) {
			Post post = postOptional.get();
			if(post.getFuncionario()!=null && post.getFuncionario().getCpf().equals(cpf)) {
				return true;
			}
		}
		
		return false;
	}
	
	public PostVisualizarDto visualisarPostId(Integer id) {
		VisualizarPostImplementacao crud = new VisualizarPostImplementacao();
		return crud.pesquisarId(id, postRepositorio);
	}
	
	public List<PostVisualizarDto> listarTodosPosts() {
		VisualizarPostImplementacao crud = new VisualizarPostImplementacao();
		return crud.listarTodos(postRepositorio);
	}
	

	public List<PostVisualizarDto> listarTodosMeusPosts(HttpSession session) {
		String cpf = (String) session.getAttribute("cpf");
		VisualizarPostPorParametroImplementacao crud = new VisualizarPostPorParametroImplementacao();
		return crud.listarTodos(cpf,postRepositorio);
	}
	
	@Transactional
	public String curtirPost(Integer id, HttpSession session) {
		Optional<Post> postOptional = postRepositorio.findById(id);
		if(postOptional.isPresent()) {
			Post post = postOptional.get();
			String cpf = (String) session.getAttribute("cpf");
			
			if(post.getListaFuncionariosCurtidores().stream().filter(f-> f.getCpf().equals(cpf)).count() == 0) 
			{
				Usuario usuario =(Usuario) usuarioService.pesquisaUsuario(cpf);
				Funcionario funcionario = new Funcionario();
				funcionario.setId(usuario.getId());
				post.getListaFuncionariosCurtidores().add(funcionario);
				postRepositorio.saveAndFlush(post);
				return "Curtiu";
			}else {
				List<Funcionario> funcionarioCurtidores = post.getListaFuncionariosCurtidores().stream().filter(f-> !f.getCpf().equals(cpf)).collect(Collectors.toList());
				post.setListaFuncionariosCurtidores(funcionarioCurtidores);
				postRepositorio.saveAndFlush(post);
				return "Descurtiu";
			}
			
		}
		
		throw new RecursoNaoEncontradoException("Post Nao encontrado");
		
	}
}
