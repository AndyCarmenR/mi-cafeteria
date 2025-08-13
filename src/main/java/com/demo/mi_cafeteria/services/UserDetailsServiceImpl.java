package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.entity.Role;
import com.demo.mi_cafeteria.model.entity.UsuarioPWD;
import com.demo.mi_cafeteria.repository.UsuarioPWDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private UsuarioPWDRepository usuarioPWDRepository;

    @Autowired
    public UserDetailsServiceImpl(UsuarioPWDRepository usuarioPWDRepository){
        this.usuarioPWDRepository=usuarioPWDRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        UsuarioPWD userPwd=usuarioPWDRepository.getUserByNickname(nickname);
        if (userPwd == null) {
            throw new UsernameNotFoundException("usuario no encontrado");
        }

        String authorities=userPwd.getUsuarioInfo().getRoles().stream()
                .map(Role::getNombreRol)
                .collect(Collectors.joining(", "));

        return org.springframework.security.core.userdetails.User
                .withUsername(userPwd.getNickname())
                .password(userPwd.getPassword())
                .roles(authorities)
                .build();
    }


}
