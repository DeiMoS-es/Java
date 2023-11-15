package com.example.user.service.impl;

import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;
import com.example.user.util.MensajeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public ResponseEntity<?> buscarPorNombre(String userName) {
        Optional<User> optionalUser = userRepository.findByUsername(userName);
        if(optionalUser.isPresent()){
            return ResponseEntity.ok(optionalUser.get());
        }else{
            String mensajeError = "El usuario con nombre: " + userName + " no se ha encontrado";
            MensajeUtil.mensajeError(mensajeError);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
}
