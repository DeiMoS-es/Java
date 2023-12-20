package com.tiendaProductos.security.service;

import com.tiendaProductos.security.auth.AuthResponse;
import com.tiendaProductos.security.auth.LoginRequest;
import com.tiendaProductos.security.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
        return null;
    }
}
