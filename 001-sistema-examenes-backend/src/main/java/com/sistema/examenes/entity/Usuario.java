package com.sistema.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@NotNull
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private boolean enable = true;
    private String perfil;
    //Un usuario puede tener MUCHOS roles
    //mappedBy -> apunta a la entidad propietaria de la relación,
    //especifica claves ajenas en la relación
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioRol> usuarioRoles = new HashSet<>();

    public Usuario() {
    }

    public void setUsername (String username){ this.username = username;}

    //Obtener todos los Authorities, creo un conjunto de ese tipo y recorro la tabla usuarioRoles
    //Obtengo su nombre y lo retorno
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> autoridades = new HashSet<>();
        this.usuarioRoles.forEach(usuarioRol -> {
            autoridades.add(new Authority(usuarioRol.getRol().getNombre()));
        });
        return autoridades;
    }

    //Por defecto return = false, pero se lo cambiamos a true para indicar que va a estar activa durante cierto tiempo
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //Por defecto return = false, pero se lo cambiamos a true para indicar que va a estar activa durante cierto tiempo
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //Le decimos que las credenciales no van a expirar, cambiamos el return a true
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
