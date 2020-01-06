package com.julia.api.catchup.interfaces;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper
public interface MapperCatchupInterface <T,E>{

	
	public E dtoParaEntidade(T element);
	
	public T entidadeParaDto(E element);
	
	public List<T> listaEntidadeParaDto(List<E> element);
	
}
