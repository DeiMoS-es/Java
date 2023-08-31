package com.example.demojwt.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Esta clase va a contener todos los filtros necesarios y el método security filter chain
 * Con la anotación Configuration, indicamos que es una clase de configuración, y contendrá métodos que van a estar anotdos con la anotation @Bean
 * que se utilizarán para configurar
 * Csrf es una token de seguridad para las peticiones POST, como en este caso vamos a trabajar con Jwt, lo deshabilitamos
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())//Esta es la nueva forma de desactivar el csrf, pasandole una función lambda
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll() //Todos los request que vayan por la ruta /auth (ej: login y registro) publicos
                                .anyRequest().authenticated() //Cualquier otra request estarán protegidas
                )
                .formLogin(Customizer.withDefaults())//Llamamos al formulario por defecto que nos provee spring
                .build();
    }
}
