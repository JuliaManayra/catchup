package com.julia.api.catchup.implementacao;

import java.util.Optional;

import com.julia.api.catchup.dominio.Post;
import com.julia.api.catchup.dominio.dto.PostEditarDto;
import com.julia.api.catchup.interfaces.EditarCatchupInterface;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.repositorio.PostRepositorio;

public class EditarPostImplementacao implements EditarCatchupInterface<PostEditarDto, Integer, PostRepositorio> {

	@Override
	public Boolean editar(PostEditarDto entidade, PostRepositorio element) {
		MapperCatchupInterface<PostEditarDto, Post> mapper= new MapperPostEditarImplementacao();
		Post Post = mapper.dtoParaEntidade(entidade);
		Optional<Post> PostAntigoOptional = element.findById(Post.getId());
		if(PostAntigoOptional.isPresent()) {
			Post atualizado= PostAntigoOptional.get();
			atualizado.setDescricao(Post.getDescricao());
			atualizado.setImagem(Post.getImagem());
			atualizado.setTitulo(Post.getTitulo());
			element.saveAndFlush(atualizado);
		}
		if (Post.getId() != null) {
			return true;
		}
		return false;
	}

}
