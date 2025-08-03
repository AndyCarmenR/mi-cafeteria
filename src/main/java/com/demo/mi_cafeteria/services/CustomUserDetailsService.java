package com.demo.mi_cafeteria.services;

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
