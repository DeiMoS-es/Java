package com.tiendaProductos.security.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {
    //Aquí estarán los métodos protegidos de nuestra api
    @PostMapping(value = "demo")
    public String welcome(){
        return "Welcome form secure endpoint";
    }
}
