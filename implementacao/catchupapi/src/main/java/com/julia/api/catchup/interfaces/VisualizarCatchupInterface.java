package com.julia.api.catchup.interfaces;

import java.util.List;

public interface VisualizarCatchupInterface <T,ID,E>{

	public List<T> listarTodos(E element);
	public T pesquisarId(ID id,E entidade);
	
}
