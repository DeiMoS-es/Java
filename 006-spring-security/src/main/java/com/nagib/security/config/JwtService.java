package com.nagib.security.config;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

@Service
public class JwtService {
    public String extractUsername(String token) {

        return null;
    }

    //Método para extraer todos los Claims(reclamos)
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()//Construye un objeto responsable de analizar y verificar tokens
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

}
