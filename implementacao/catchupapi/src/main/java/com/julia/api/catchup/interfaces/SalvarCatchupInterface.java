package com.julia.api.catchup.interfaces;

public interface SalvarCatchupInterface <T,ID,E>{

	public Boolean salvar(T entidade,E element);
	
}
