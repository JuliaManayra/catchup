package com.julia.api.catchup.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.julia.api.catchup.dominio.Post;
@Repository
public interface PostRepositorio extends JpaRepository<Post,Integer>{

}
