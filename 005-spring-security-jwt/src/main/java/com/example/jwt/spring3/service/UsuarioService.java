package com.example.jwt.spring3.service;

import com.example.jwt.spring3.entity.Usuario;
import com.example.jwt.spring3.entity.UsuarioRol;

import java.util.Set;

public interface UsuarioService {

    Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRols) throws Exception;
    Usuario obtenerUsuarioUsername(String userName);
    void eliminarUsuarioId(Integer idUsuario);
    void eliminarUsuarioUsername(String userName);
}
