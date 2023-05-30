package com.sistema.examenes.secutiry;

import com.sistema.examenes.services.impl.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    //Un filtro se encarga de interceptar todas las llamadas al servidor y comprueba la existencia del token
    //lo desencripta y valida, si está Ok añade las configuraciones necesarias para las peticiones
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7); //7 porque la longitud de Bearer + el espacio es 7 y yo quiero el token que viene después
            try {
                username = this.jwtUtils.extractUsername(jwtToken);//extraemos del token recortado el username
            }catch (ExpiredJwtException expiredJwtException){
                System.out.println("El token ha expirado");
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }else{
            System.out.println("Token invalido, no empieza con beare string");//Para que un token sea válido ha de empezar siempre con Bearer
        }
        //validamos la peticion (request)
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){//Si la autenticacion no es válida
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.jwtUtils.validateToken(jwtToken, userDetails)){//token y usuario
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            }
        }
    }
}
