package com.tiendaProductos.security.controller;

import com.tiendaProductos.security.auth.AuthResponse;
import com.tiendaProductos.security.auth.LoginRequest;
import com.tiendaProductos.security.auth.RegisterRequest;
import com.tiendaProductos.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    //Para el login el tipo de respuesta será AuthResponse, ya que es la clase encargada de devolver el token
    //En el cuerpo del mensaje accedemos a las credenciales del usuario, definidas en la clase LoginRequest
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request)); // la respuesta será el token
    }
    @GetMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
