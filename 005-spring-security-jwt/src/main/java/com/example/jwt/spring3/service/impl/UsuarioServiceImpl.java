package com.example.jwt.spring3.service.impl;

import com.example.jwt.spring3.entity.Usuario;
import com.example.jwt.spring3.entity.UsuarioRol;
import com.example.jwt.spring3.repository.RolRepository;
import com.example.jwt.spring3.repository.UsuarioRepository;
import com.example.jwt.spring3.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRols) throws Exception {
        Usuario usuarioSave = usuarioRepository.findByUserName(usuario.getUserName());
        // TODO: 06/06/2023 añadir verificaciones de campos vacíos y codificar password, añadir también que guarde un rol por defecto
        if (usuarioSave != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya está en la bbdd");
        }else{
            for(UsuarioRol usuarioRol:usuarioRols){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRols);
            usuarioSave = usuarioRepository.save(usuario);
        }
        return usuarioSave;
    }

    @Override
    public Usuario obtenerUsuarioUsername(String userName) {
        return usuarioRepository.findByUserName(userName);
    }

    @Override
    public void eliminarUsuarioId(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    @Override
    public void eliminarUsuarioUsername(String userName) {
        Usuario usuario = usuarioRepository.findByUserName(userName);
        usuarioRepository.deleteById(usuario.getIdUsuario());
    }
}
