package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.entity.Login;
import com.demo.mi_cafeteria.model.entity.UsuarioInfo;
import com.demo.mi_cafeteria.model.entity.UsuarioPWD;
import com.demo.mi_cafeteria.model.requests.RegistryRequest;
import com.demo.mi_cafeteria.repository.LoginRepository;
import com.demo.mi_cafeteria.repository.UsuarioPWDRepository;
import com.demo.mi_cafeteria.utils.Constants;
import com.demo.mi_cafeteria.utils.NotFoundException;
import com.demo.mi_cafeteria.utils.NullOrWhiteSpaceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPwdService {

    @Autowired
    private UsuarioPWDRepository usuarioPWDRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;




    public UsuarioPWD crearUsuarioPwd(RegistryRequest request, UsuarioInfo usuarioInfo){
        if (NullOrWhiteSpaceUtils.isNull(request.getNickname()) || NullOrWhiteSpaceUtils.isBlankOrWhiteSpace(request.getNickname())
            ||NullOrWhiteSpaceUtils.isNull(request.getPwdUsuario()) || NullOrWhiteSpaceUtils.isBlankOrWhiteSpace(request.getPwdUsuario())) {
            return null;
        }
        UsuarioPWD usuarioPWD=usuarioPWDRepository.getUserByNickname(request.getNickname());
        if (usuarioPWD == null ) {
            usuarioPWD=new UsuarioPWD();
            usuarioPWD.setNickname(request.getNickname());
            usuarioPWD.setPassword(passwordEncoder.encode(request.getPwdUsuario()));
            //System.out.println(usuarioPWD.getPassword());
            usuarioPWD.setUsuarioInfo(usuarioInfo);
            return usuarioPWDRepository.save(usuarioPWD);
        }
        if (request.isUpdating()) {
            usuarioPWD.setNickname(request.getNickname());
            usuarioPWD.setPassword(passwordEncoder.encode(request.getPwdUsuario()));
            usuarioPWD.setUsuarioInfo(usuarioInfo);

            return usuarioPWDRepository.save(usuarioPWD);
        }
        return usuarioPWD;
    }



    public String login(String nickname, String password){

       /*try {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(nickname,password) );
        }catch (AuthenticationException e){
            return "No autorizado";
        }*/
        UsuarioPWD userPwd=usuarioPWDRepository.getUserByNickname(nickname);
        if (userPwd == null) {
            return Constants.NICKNAME_O_PWD_INCORRECTOS;
        }
        if (!password.equals(userPwd.getPassword())){
            return Constants.NICKNAME_O_PWD_INCORRECTOS;
        }

        UserDetails userDetails= userDetailsService.loadUserByUsername(nickname);

        Login login=new Login();
        login.setUsuarioPWD(userPwd);
        String token=jwtService.generarToken(userPwd);
        login.setToken(token);
        try {
            loginRepository.save(login);
        } catch (Exception e) {
            return Constants.HA_OCURRIDO_UN_ERROR_LOGIN.replace("_ERROR_",e.getMessage());
        }
        return login.getToken();
    }

    public String updateUserPWD(RegistryRequest request){
        if (NullOrWhiteSpaceUtils.isBlankOrWhiteSpace(request.getNickname()) || NullOrWhiteSpaceUtils.isBlankOrWhiteSpace(request.getPwdUsuario())) {
            return "El nick name del usuario o el password ingresado son nulos o vacios, por favor revise sus datos";
        }
        UsuarioPWD usuarioPWD=usuarioPWDRepository.getUserByNickname(request.getNickname());
        if (usuarioPWD == null) {
            return "No hemos encontrado el usuario al que intenta cambiar la contraseña, por favor revise sus datos.";
        }
        usuarioPWD.setPassword(passwordEncoder.encode(request.getPwdUsuario()));
        try {
            usuarioPWDRepository.save(usuarioPWD);
        } catch (Exception e) {
            return "ha habido un error inesperado en el sistema, por favor contacte a soporte (por favor no nos contacte :'c)";
        }
        return null;
    }
    public UsuarioPWD getAuthenicatedUser(){
        String nickname=SecurityContextHolder.getContext().getAuthentication().getName();
        UsuarioPWD userPwd=usuarioPWDRepository.getUserByNickname(nickname);
        if (userPwd == null) {
            throw new NotFoundException("No se ha encontrado el usuario");
        }
        return userPwd;
    }
}
