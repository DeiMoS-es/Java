package com.apiRest.user.controller;

import com.apiRest.user.entity.User;
import com.apiRest.user.repository.UserRepository;
import com.apiRest.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class  UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/buscarUsuarioNombre/{userName}")
    public ResponseEntity<?> buscarPorNombre(@PathVariable("userName") String userName){
        return userService.buscarPorNombre(userName);
    }
}
