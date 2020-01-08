package com.julia.api.catchup.implementacao;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.julia.api.catchup.dominio.Post;
import com.julia.api.catchup.dominio.dto.PostVisualizarDto;
import com.julia.api.catchup.interfaces.MapperCatchupInterface;

public class MapperPostVisualizarImplementacao implements MapperCatchupInterface<PostVisualizarDto,Post >{


	@Override
	public Post dtoParaEntidade(PostVisualizarDto  element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, Post.class);
	}

	@Override
	public PostVisualizarDto entidadeParaDto(Post  element) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
				.setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);

		return modelMapper.map(element, PostVisualizarDto.class);
	}

	@Override
	public List<PostVisualizarDto> listaEntidadeParaDto(List<Post> element) {
		return element.stream().map(this::entidadeParaDto)
				.collect(Collectors.toList());
	}

	
}
