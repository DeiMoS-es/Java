package com.example.demo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //registry.addMapping("/api/contactos") en el caso de que la app fuese a crecer
               registry.addMapping("/api/**")
                       .allowedOrigins("htt://localhost:4200")
                       .allowedMethods("*");
            }
        };
    }
}
