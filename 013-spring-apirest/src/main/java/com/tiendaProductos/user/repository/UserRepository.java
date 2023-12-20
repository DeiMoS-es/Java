package com.tiendaProductos.user.repository;

import com.tiendaProductos.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<User, Long> {
    //QueryMethod para buscar por nombre de usuario
    Optional<User> findByUserName(String username);
}
