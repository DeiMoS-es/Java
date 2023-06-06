package com.example.jwt.spring3.repository;

import com.example.jwt.spring3.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findOneByEmail(String email);
    Usuario findByUserName(String userName);
}
