package com.apiRest.security.auth.controller;

import com.apiRest.security.entity.AuthResponse;
import com.apiRest.security.entity.LoginRequest;
import com.apiRest.security.entity.RegisterRequest;
import com.apiRest.security.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        System.out.println("----------------------------------------");
        System.out.println(authService.login(request));
        System.out.println("----------------------------------------");
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
