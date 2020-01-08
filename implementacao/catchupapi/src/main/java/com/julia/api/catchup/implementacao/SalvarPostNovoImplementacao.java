package com.julia.api.catchup.implementacao;

import com.julia.api.catchup.dominio.Post;
import com.julia.api.catchup.dominio.dto.PostNovoDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;
import com.julia.api.catchup.interfaces.SalvarCatchupInterface;
import com.julia.api.catchup.repositorio.PostRepositorio;

public class SalvarPostNovoImplementacao implements   SalvarCatchupInterface<PostNovoDto, Integer,PostRepositorio>{

	@Override
	public Boolean salvar(PostNovoDto entidade, PostRepositorio repositorio) {
		MapperCatchupInterface<PostNovoDto, Post> mapper= new MapperPostNovoImplementacao();
		Post Post = mapper.dtoParaEntidade(entidade);
		
		repositorio.saveAndFlush(Post);

		if (Post.getId() != null) {
			return true;
		}
		return false;
		
	}

}
