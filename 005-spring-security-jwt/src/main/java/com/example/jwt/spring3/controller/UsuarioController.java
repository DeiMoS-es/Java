package com.example.jwt.spring3.controller;

import com.example.jwt.spring3.dto.UsuarioDTO;
import com.example.jwt.spring3.entity.Rol;
import com.example.jwt.spring3.entity.Usuario;
import com.example.jwt.spring3.entity.UsuarioRol;
import com.example.jwt.spring3.repository.UsuarioRepository;
import com.example.jwt.spring3.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public List<Usuario> listarUsuario(){
        return usuarioRepository.findAll();
    }

    @PostMapping("/guardar")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        //usuario.setPerfil("default.png");

        Set<UsuarioRol> usuarioRols = new HashSet<>();
        Rol rol = new Rol();
        rol.setRolId(1);;
        rol.setNombreRol("USUARIO");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRols.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario, usuarioRols);
    }

    @PutMapping("")

    @GetMapping("/buscarNombre/{username}")
    /*Para los métodos de buscar un usuario, he decidido usar un DTO ya ya que se va a
     * mostrar información y hay campos como el de la contraseña o el de la fecha de alta
     * que en principio no se quiere mostrar
     */
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable("username") String username){
        Usuario usuario = usuarioService.obtenerUsuarioUsername(username);
        if(usuario != null){
            UsuarioDTO usuarioDTO = UsuarioDTO.fromUsuario(usuario);
            return ResponseEntity.ok(usuarioDTO);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<UsuarioDTO> getUsuario(@PathVariable("id") Integer id){
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if(optionalUsuario.isPresent()){
            Usuario usuario = optionalUsuario.get();//Convertimos el optionalUsuario en un usuario
            UsuarioDTO usuarioDTO = UsuarioDTO.fromUsuario(usuario);//Transformamos el usuario en un usuarioDTO
            return ResponseEntity.ok(usuarioDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Integer usuarioId){
        usuarioService.eliminarUsuarioId(usuarioId);
    }
}
