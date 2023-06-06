package com.example.jwt.spring3.dto;

import com.example.jwt.spring3.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String userName;
    private String telefono;
    private String email;
    private String perfil;

    public static UsuarioDTO fromUsuario (Usuario usuario){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(usuario.getNombre());
        dto.setPrimerApellido(usuario.getPrimerApellido());
        dto.setSegundoApellido(usuario.getSegundoApellido());
        dto.setUserName(usuario.getUserName());
        dto.setTelefono(usuario.getTelefono());
        dto.setEmail(usuario.getEmail());
        dto.setPerfil(usuario.getPerfil());
        return dto;
    }
}
