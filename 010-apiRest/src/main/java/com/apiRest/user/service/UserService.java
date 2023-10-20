package com.apiRest.user.service;

import com.apiRest.user.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> buscarPorNombre (String userName);
}
