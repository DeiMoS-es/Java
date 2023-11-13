package com.example.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String token = getTokenFromRequest(request);

        if (token!= null) {
            filterChain.doFilter(request, response);//devolvemos el control a la cadena de filtros
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        //Este método nos devuelve el token de la cabecera de la petición
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);//buscamos en el encabezado la propiedad de autenticación
        if (StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);//Nos quedamos con los caracteres desde el 7 en adelante, desde el 7 porque es a partir del Bearer + un espacio
        }
        return null;
    }
}
