package com.tiendaProductos.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/login")
    public String login(){
        return "Login from public endpoint";
    }
    @GetMapping("/register")
    public String register(){
        return "Register from public endpoint";
    }
}
