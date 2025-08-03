package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.entity.Login;
import com.demo.mi_cafeteria.model.entity.UsuarioInfo;
import com.demo.mi_cafeteria.model.entity.UsuarioPWD;
import com.demo.mi_cafeteria.model.responses.AuthResponse;
import com.demo.mi_cafeteria.repository.LoginRepository;
import com.demo.mi_cafeteria.repository.UsuarioPWDRepository;
import com.demo.mi_cafeteria.utils.Constants;
import com.demo.mi_cafeteria.utils.NullOrWhiteSpaceUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UsuarioPWD crearUsuarioPwd(AuthResponse.RegistryRequest request, UsuarioInfo usuarioInfo){

        if (NullOrWhiteSpaceUtils.isNull(request.getNickname()) || NullOrWhiteSpaceUtils.isBlankOrWhiteSpace(request.getNickname())
            ||NullOrWhiteSpaceUtils.isNull(request.getPwdUsuario()) || NullOrWhiteSpaceUtils.isBlankOrWhiteSpace(request.getPwdUsuario())) {
            return null;
        }
        UsuarioPWD usuarioPWD=new UsuarioPWD();
        usuarioPWD.setNickname(request.getNickname());
        usuarioPWD.setPassword(request.getPwdUsuario());
        usuarioPWD.setUsuarioInfo(usuarioInfo);

        return usuarioPWDRepository.save(usuarioPWD);
    }

    public String login(String nickname, String password){
        UsuarioPWD userPwd=usuarioPWDRepository.getUserByNickname(nickname);
        if (userPwd == null) {
            return Constants.NICKNAME_O_PWD_INCORRECTOS;
        }
        if (!passwordEncoder.matches(password, userPwd.getPassword())){
            return Constants.NICKNAME_O_PWD_INCORRECTOS;
        }
        Login login=new Login();
        login.setUsuarioPWD(userPwd);
        login.setToken(jwtService.generarToken(userPwd.getNickname()));
        try {
            loginRepository.save(login);
        } catch (Exception e) {
            return Constants.HA_OCURRIDO_UN_ERROR_LOGIN.replace("_ERROR_",e.getMessage());
        }
        return login.getToken();
    }
}
