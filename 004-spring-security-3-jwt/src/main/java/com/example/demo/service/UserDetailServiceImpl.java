package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*Esta clase se va a encargar de cargar un usuario desde la bbdd */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Usuario usuario = usuarioRepository
                .findOneByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario con email "+email+" noexiste"));
      return new UserDetailsImpl(usuario);
    }
}
