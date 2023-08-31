package com.example.jwt.spring3.service.impl;

import com.example.jwt.spring3.entity.Usuario;
import com.example.jwt.spring3.entity.UsuarioRol;
import com.example.jwt.spring3.repository.RolRepository;
import com.example.jwt.spring3.repository.UsuarioRepository;
import com.example.jwt.spring3.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

import java.util.Date;
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
            if(usuario.getUserName().isEmpty() || usuario.getUserName().isBlank()){
                throw  new Exception("El nombre de usuario no puede estar vacío");
            }
            if(usuario.getPassword().isEmpty() || usuario.getPassword().isBlank()){
                throw  new Exception("La contraseña no puede estar vacía");
            }
            if(usuario.getNombre().isEmpty() || usuario.getNombre().isBlank()){
                throw  new Exception("El nombre no puede estar vacía");
            }
            if(usuario.getEmail().isEmpty() || usuario.getEmail().isBlank()){
                throw  new Exception("El email no puede estar vacío");
            }
            usuario.setPerfil("default.png");
            usuario.setFechaAlta(LocalDateTime.now());
            usuario.getUsuarioRoles().addAll(usuarioRols);
            usuario.setPerfil("default.png");
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
