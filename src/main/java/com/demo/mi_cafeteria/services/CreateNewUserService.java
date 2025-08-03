package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.dto.UsuarioDto;
import com.demo.mi_cafeteria.model.entity.UsuarioInfo;
import com.demo.mi_cafeteria.model.entity.UsuarioPWD;
import com.demo.mi_cafeteria.model.responses.AuthResponse;
import com.demo.mi_cafeteria.model.responses.RegistryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNewUserService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserPwdService userPwdService;

    public RegistryResponse crearNuevoUsuario(AuthResponse.RegistryRequest request){
        UsuarioInfo userInfo=userInfoService.crearInfoUsuario(request);

        if (userInfo == null) {
            return new RegistryResponse(null, "ocurrio un error, creando su informacion, revise sus parametros");
        }
        UsuarioPWD userPwd=userPwdService.crearUsuarioPwd(request, userInfo);
        if (userPwd == null) {
            return new RegistryResponse(null, "ocurrio un error, creando su contraseña, revise sus parametros");
        }
        UsuarioDto userDto=new UsuarioDto(userInfo);
        return new RegistryResponse(userDto,"success");
    }
}
