package com.julia.api.catchup.interfaces;

import java.util.List;


public interface VisualizarPorParametroCatchupInterface <T,P,E>{

	public List<T> listarTodos(P parametro, E element);
	
}
