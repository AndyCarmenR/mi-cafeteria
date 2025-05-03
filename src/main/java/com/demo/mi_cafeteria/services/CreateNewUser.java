package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.RegistryRequest;
import com.demo.mi_cafeteria.model.RegistryResponse;
import com.demo.mi_cafeteria.model.UsuarioInfo;
import com.demo.mi_cafeteria.model.UsuarioPWD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateNewUser {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserPwdService userPwdService;

    public RegistryResponse crearNuevoUsuario(RegistryRequest request){
        UsuarioInfo userInfo=userInfoService.crearInfoUsuario(request);
        if (userInfo == null) {
            return new RegistryResponse(null,null, "ocurrio un error, creando su informacion, revise sus parametros");
        }
        UsuarioPWD userPwd=userPwdService.crearUsuarioPwd(request, userInfo);
        if (userPwd == null) {
            return new RegistryResponse(null,null, "ocurrio un error, creando su contraseña, revise sus parametros");
        }
        return new RegistryResponse(userInfo,userPwd,"success");
    }
}
