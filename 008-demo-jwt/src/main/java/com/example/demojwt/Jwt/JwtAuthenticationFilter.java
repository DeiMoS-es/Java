package com.example.demojwt.Jwt;

import com.example.demojwt.Jwt.Services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Se extiende de la clase abstracta OncePerRequestFilter, para crear filtros personalizados
 * De esta forma garantizamos que el filtro se ejecute una vez por cada solicitud http, incluso si hay múltiples filtros
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtenemos el token del request
        final String token = getTokenFromRquest(request);
        final String username;

        if(token == null){
            filterChain.doFilter(request, response);//Le devolvemos el control a la cadena de filtros
            return;
        }
        username = jwtService.getUsernameFromToken(token);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); //Busco al usuario en bbdd
            if(jwtService.isTokenValid(token, userDetails)){//si el token es valido
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                //Seteamos el details
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
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
