package com.tiendaProductos.security.service;

import com.tiendaProductos.security.auth.AuthResponse;
import com.tiendaProductos.security.auth.LoginRequest;
import com.tiendaProductos.security.auth.RegisterRequest;
import com.tiendaProductos.user.entity.Role;
import com.tiendaProductos.user.entity.User;
import com.tiendaProductos.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .nombre(request.getNombre())
                .apellidos(request.getApellidos())
                .pais(request.getPais())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
