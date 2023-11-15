package com.example.user.service;

import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> buscarPorNombre (String userName);
}
