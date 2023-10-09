package com.apiRest.security.jwt;

import com.apiRest.security.jwt.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import org.springframework.http.HttpHeaders;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //filterchain es la cadena de filtros que ya hemos configurado
        //Obtener el token del request
        final String token = getTokenFromRequest(request);
        final String username;
        if(token == null){ // si el token era nulo devolvemos el control a la cadena de filtros
            filterChain.doFilter(request, response);
            return;
        }
        username = jwtService.getUserNameFromToken(token);
        System.out.println(username);
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtService.isTokenValid(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
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
