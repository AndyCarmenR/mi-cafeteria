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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;

@Service
public class UserPwdService {

    private static final Logger log = LoggerFactory.getLogger(UserPwdService.class);

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

        log.info("creando contraseña de usuario para: {}",request.getNickname());

        if (NullOrWhiteSpaceUtils.isNull(request.getNickname()) || NullOrWhiteSpaceUtils.isBlankOrWhiteSpace(request.getNickname())
            ||NullOrWhiteSpaceUtils.isNull(request.getPwdUsuario()) || NullOrWhiteSpaceUtils.isBlankOrWhiteSpace(request.getPwdUsuario())) {
            log.info("Parametros nulos o con cadenas vacias para usuario: {}, finalizando metodo",request.getNickname());
            return null;
        }
        UsuarioPWD usuarioPWD = null;
        try {
            usuarioPWD=usuarioPWDRepository.getUserByNickname(request.getNickname());
        }catch (Exception exception){
            log.error("Error buscando la contraseña del usuario {}",request.getNickname());
            log.error("Error: {}", exception.getMessage());
        }

        if (usuarioPWD == null ) {
            log.info("No se encontró usuario registrado, creando nuevo registro para: {}",request.getNickname());
            usuarioPWD=new UsuarioPWD();
            usuarioPWD.setNickname(request.getNickname());
            usuarioPWD.setPassword(passwordEncoder.encode(request.getPwdUsuario()));
            //System.out.println(usuarioPWD.getPassword());
            usuarioPWD.setUsuarioInfo(usuarioInfo);
            try{
                log.info("antes de guardar nuevo registro de contraseña: {}",request.getNickname());
                return usuarioPWDRepository.save(usuarioPWD);
            }catch (Exception e){
                log.error("Ha ocurrido un error al guardar el nuevo registro de contraseña para {}",request.getNickname());
                log.error("Error: {}",e.getMessage());
            }

        }
        if (request.isUpdating()) {
            log.info("iniciando la actualizacion registro para: {}",request.getNickname());
            usuarioPWD.setNickname(request.getNickname());
            usuarioPWD.setPassword(passwordEncoder.encode(request.getPwdUsuario()));
            usuarioPWD.setUsuarioInfo(usuarioInfo);
            try {
                log.info("guardando la actualizacion registro para: {}",request.getNickname());
                return usuarioPWDRepository.save(usuarioPWD);
            } catch (Exception e) {
                log.info("Error guardando la actualizacion del usuario");
                log.error(e.getMessage());
            }
        }
        log.info("el usuario {} fue encontrado, pero no se necesitaba una actualizacion",request.getNickname());
        return usuarioPWD;
    }



    public String login(String nickname, String password){
        log.info("iniciando login para usuario {}",nickname);
        UsuarioPWD userPwd = null;
        try {
            log.info("busqueda de contraseña para el usuario {}",nickname);
            userPwd=usuarioPWDRepository.getUserByNickname(nickname);
        } catch (Exception e) {
            log.error("error buscando la contraseña para el usuario {}",nickname);
            log.error("Error {}",e.getMessage());
            return "ERROR "+e.getMessage();
        }

        if (userPwd == null) {
            return Constants.NICKNAME_O_PWD_INCORRECTOS;
        }
        if (!password.equals(userPwd.getPassword())){
            return Constants.NICKNAME_O_PWD_INCORRECTOS;
        }

        log.info("guardando datos de login para usuario {}",nickname);
        Login login=new Login();
        login.setUsuarioPWD(userPwd);
        String token=jwtService.generarToken(userPwd);
        login.setToken(token);
        login.setFechaLogin(LocalDate.now());
        try {
            log.info("antes de iniciar guardando datos de login para usuario {}",nickname);
            loginRepository.save(login);
        } catch (Exception e) {
            log.error("Error {}",e.getMessage());
            return Constants.HA_OCURRIDO_UN_ERROR_LOGIN.replace("_ERROR_",e.getMessage());
        }
        return login.getToken();
    }

    public String updateUserPWD(RegistryRequest request){
        log.info("Iniciando metodo para actualizar contraseña del usuario {}",request.getNickname());
        if (NullOrWhiteSpaceUtils.isBlankOrWhiteSpace(request.getNickname()) || NullOrWhiteSpaceUtils.isBlankOrWhiteSpace(request.getPwdUsuario())) {
            log.info("parametros de request incompletos para usuario {}",request.getNickname());
            return "El nickname del usuario o el password ingresado son nulos o vacios, por favor revise sus datos";
        }
        log.info("buscando registro de contraseña para usuario {}",request.getNickname());
        UsuarioPWD usuarioPWD=null;
        try{
            usuarioPWD=usuarioPWDRepository.getUserByNickname(request.getNickname());
        }catch (Exception e){
            log.error("Error buscando registro de contraseña para usuario: {}",request.getNickname());
            log.error(e.getMessage());
            return "ERROR "+e.getMessage();
        }
        if (usuarioPWD == null) {
            log.info("No se encontro registro de contraseña para usuario {}",request.getNickname());
            return "No hemos encontrado el usuario al que intenta cambiar la contraseña, por favor revise sus datos.";
        }
        usuarioPWD.setPassword(passwordEncoder.encode(request.getPwdUsuario()));
        try {
            log.info("Actualizando contraseña para usuario {}",request.getNickname());
            usuarioPWDRepository.save(usuarioPWD);
        } catch (Exception e) {
            log.error("Error: {}",e.getMessage());
            return "ha habido un error inesperado en el sistema, por favor contacte a soporte (por favor no nos contacte :'c)";
        }
        return null;
    }
    public UsuarioPWD getAuthenicatedUser(){
        log.info("buscando datos de usuario autenticado");
        String nickname=SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("nickname encontrado {}",nickname);
        UsuarioPWD userPwd=usuarioPWDRepository.getUserByNickname(nickname);
        if (userPwd == null) {
            log.info("no se encontro informacion de este usuario {}",nickname);
            return null;
        }
        return userPwd;
    }
}
