package com.julia.api.catchup.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.julia.api.catchup.dominio.Funcionario;
@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario,Integer>{
	Funcionario findByCpf(String cpf);

	
}
