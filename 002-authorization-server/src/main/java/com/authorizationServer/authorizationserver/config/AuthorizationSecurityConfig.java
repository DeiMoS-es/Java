package com.authorizationServer.authorizationserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class AuthorizationSecurityConfig {
    @Bean
    @Order(1)
    public SecurityFilterChain authSecurityFilterChain (HttpSecurity http) throws Exception{

    }

    @Bean
    @Order(2)
    public SecurityFilterChain webSecurityFilterChain (HttpSecurity http) throws Exception{

    }
}
