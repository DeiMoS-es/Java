package com.ecomerce.orders_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * WebClientConfig, clase de configuración para WebClient.
 * Esta clase será nuestro cliente HTTP para realizar peticiones a otros servicios, en este caso a inventario.
 * WebClient usa programación reactiva y flujo de datos asincronos
 */
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder getWebClient() {
        return WebClient.builder();
    }
}
