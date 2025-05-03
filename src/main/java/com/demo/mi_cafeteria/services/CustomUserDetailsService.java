package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.UsuarioPWD;
import com.demo.mi_cafeteria.repository.UsuarioPWDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
/*
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioPWDRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UsuarioPWD usuario = usuarioRepository.findByUserNickName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new User(
                usuario.getUserName(),
                usuario.getPassword(),
                Collections.emptyList() // o lista de roles si tienes
        );
    }
}*/
