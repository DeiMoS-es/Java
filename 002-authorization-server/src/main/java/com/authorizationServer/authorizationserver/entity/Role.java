package com.authorizationServer.authorizationserver.entity;

import com.authorizationServer.authorizationserver.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)//Indico que sea tipo cadena, sino la bbdd pone un nº y quiero el rol
    private RoleName role;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
