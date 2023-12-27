package com.tiendaProductos.user.controller;

import com.tiendaProductos.user.entity.User;
import com.tiendaProductos.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/buscarPorNombre/{username}")
    public ResponseEntity<User> buscarPorNombre(@PathVariable("username") String username) {
        System.out.println("Entro en controller--------------------------------");
        System.out.println(username);
        try {
            User user = userService.buscarPorNombre(username);
            return ResponseEntity.ok(user);
        } catch (UsernameNotFoundException ex) {
            // Manejo de la excepción cuando el usuario no es encontrado
            return ResponseEntity.notFound().build();
        }
    }
}
