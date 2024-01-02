package com.tiendaProductos.user.dto;

import com.tiendaProductos.user.entity.Role;
import com.tiendaProductos.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String nombre;
    private String apellidos;
    private String pais;
    Role role;

    public static UserDTO fromUser(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setNombre(user.getNombre());
        userDTO.setApellidos(user.getApellidos());
        userDTO.setPais(user.getPais());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
