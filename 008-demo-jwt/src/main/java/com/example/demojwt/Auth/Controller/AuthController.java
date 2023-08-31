package com.example.demojwt.Auth.Controller;

import com.example.demojwt.Auth.AuthResponse;
import com.example.demojwt.Auth.LoginRequest;
import com.example.demojwt.Auth.RegisterRequest;
import com.example.demojwt.Auth.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor //Anotación de Lombok para que se agregue el constructor con todos los argumentos
public class AuthController {

    private final AuthService authService;

    //para logearnos en nuestra app, vamos a hacer uso de la clase LoginRequest, que vendrá en la petición y solo contiene nombre de usuario y contraseña
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }
}
