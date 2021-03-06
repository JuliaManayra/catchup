package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.julia.api.catchup.dominio.Post;
import com.julia.api.catchup.dominio.dto.PostEditarDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;

public class MapperPostEditarImplementacao implements MapperCatchupInterface<PostEditarDto,Post >{




	@Override
	public Post dtoParaEntidade(PostEditarDto element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, Post.class);
	}



	@Override
	public PostEditarDto entidadeParaDto(Post element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, PostEditarDto.class);
	}



	@Override
	public List<PostEditarDto> listaEntidadeParaDto(List<Post> element) {
		return element.stream().map(this::entidadeParaDto)
				.collect(Collectors.toList());
	}
}
