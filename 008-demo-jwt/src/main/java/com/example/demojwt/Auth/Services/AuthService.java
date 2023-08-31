package com.example.demojwt.Auth.Services;

import com.example.demojwt.Auth.AuthResponse;
import com.example.demojwt.Auth.LoginRequest;
import com.example.demojwt.Auth.RegisterRequest;
import com.example.demojwt.User.Repository.UserRepository;
import com.example.demojwt.User.Role;
import com.example.demojwt.User.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public AuthResponse login(LoginRequest request){
        return null;
    }

    public AuthResponse register(RegisterRequest request){
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .country(request.getCountry())
                .role(Role.USER)//la primera vez que se cree un usuario tendrá rol USER
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(null)
                .build();
    }
}
