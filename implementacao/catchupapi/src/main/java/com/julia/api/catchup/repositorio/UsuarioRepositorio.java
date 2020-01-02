package com.julia.api.catchup.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julia.api.catchup.dominio.view.Usuario;
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer>{
	
	Optional<Usuario> findByCpf(String cpf);
	

}
