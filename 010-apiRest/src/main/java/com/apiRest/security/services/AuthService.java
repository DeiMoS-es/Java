package com.apiRest.security.services;

import com.apiRest.security.entity.AuthResponse;
import com.apiRest.security.entity.LoginRequest;
import com.apiRest.security.entity.RegisterRequest;
import com.apiRest.security.jwt.services.JwtService;
import com.apiRest.user.entity.Role;
import com.apiRest.user.entity.User;
import com.apiRest.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
        UserDetails user = userRepository.findByUserName(request.getUserName()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request){
        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode( request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .country(request.getCountry())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
