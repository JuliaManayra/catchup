package com.julia.api.catchup.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julia.api.catchup.dominio.Parceria;
@Repository
public interface ParceriaRepositorio extends JpaRepository<Parceria,Integer>{
	List<Parceria> findByFuncionarioCpf(String cpf);
}
