package com.apiRest.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
// Va a tener métodos con la anotacion @Bean
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())//Esta es la nueva forma de desactivar el csrf, pasandole una función lambda, es una medida de seguridad que se utiliza para agregar a las solicitudes post un csrf valido
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/auth/**").permitAll() // Estas serán publicas, porque así lo hemos decidido
                                .anyRequest().authenticated() // Para el resto pedimos authenticación
                                )
                .formLogin(withDefaults())
                .build();
    }
}
