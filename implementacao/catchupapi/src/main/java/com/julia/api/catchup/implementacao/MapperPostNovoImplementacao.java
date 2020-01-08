package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.julia.api.catchup.dominio.Post;
import com.julia.api.catchup.dominio.dto.PostNovoDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;

public class MapperPostNovoImplementacao implements MapperCatchupInterface<PostNovoDto,Post >{




	@Override
	public Post dtoParaEntidade(PostNovoDto element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(AccessLevel.PRIVATE);
		
		return modelMapper.map(element, Post.class); 	
	}



	@Override
	public PostNovoDto entidadeParaDto(Post element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, PostNovoDto.class);
	}



	@Override
	public List<PostNovoDto> listaEntidadeParaDto(List<Post> element) {
		return element.stream().map(this::entidadeParaDto)
				.collect(Collectors.toList());
	}
}
