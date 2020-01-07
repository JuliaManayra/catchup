package com.julia.api.catchup.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julia.api.catchup.dominio.Aviso;
@Repository
public interface AvisoRepositorio extends JpaRepository<Aviso,Integer>{

	List<Aviso> findByFuncionarioCpf(String cpf);
	
}
