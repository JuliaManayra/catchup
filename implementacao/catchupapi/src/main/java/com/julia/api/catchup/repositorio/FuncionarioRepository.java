package com.julia.api.catchup.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.julia.api.catchup.dominio.Funcionario;
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Integer>{

}
