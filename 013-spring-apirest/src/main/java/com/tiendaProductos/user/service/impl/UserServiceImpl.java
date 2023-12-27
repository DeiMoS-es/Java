package com.tiendaProductos.user.service.impl;

import com.tiendaProductos.user.entity.User;
import com.tiendaProductos.user.repository.UserRepository;
import com.tiendaProductos.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User buscarPorNombre(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con nombre: " + username));
    }
}
