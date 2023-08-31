package com.example.demojwt.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Se extiende de la clase abstracta OncePerRequestFilter, para crear filtros personalizados
 * De esta forma garantizamos que el filtro se ejecute una vez por cada solicitud http, incluso si hay múltiples filtros
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtenemos el token del request
        final String token = getTokenFromRquest(request);

        if(token == null){
            filterChain.doFilter(request, response);//Le devolvemos el control a la cadena de filtros
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRquest(HttpServletRequest request) {
        //Importante! HttpHeaders -> del paquete (import org.springframework.http.HttpHeaders;)
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7); //Nos quedamos con los caracteres desde el 7 en adelante, desde el 7 porque es a partir del Bearer + un espacio
        }
        return null;
    }
}
