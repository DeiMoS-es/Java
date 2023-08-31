package com.example.demojwt.Auth.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor //Anotación de Lombok para que se agregue el constructor con todos los argumentos
public class AuthController {
    @PostMapping(value = "login")
    public String login(){
        return "Login from publick endpoint";
    }

    @PostMapping(value = "register")
    public String register(){
        return "Register from public endpoint";
    }
}
