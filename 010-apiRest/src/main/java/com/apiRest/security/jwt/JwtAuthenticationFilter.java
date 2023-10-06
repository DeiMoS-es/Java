package com.apiRest.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import org.springframework.http.HttpHeaders;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //filterchain es la cadena de filtros que ya hemos configurado
        //Obtener el token del request
        final String token = getTokenFromRequest(request);
        if(token == null){ // si el token era nulo devolvemos el control a la cadena de filtros
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        //En el encabezado del request es donde obtenemos el token, este string comenzará con la palabra Bearer
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        //Tengo que confirmar que existe Bearer
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            //extraigo el token y lo devuelvo
            return authHeader.substring(7);
        }
        return null;
    }
}
