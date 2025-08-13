package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.dto.RolDto;
import com.demo.mi_cafeteria.model.dto.UsuarioDto;
import com.demo.mi_cafeteria.model.entity.Role;
import com.demo.mi_cafeteria.model.entity.UsuarioInfo;
import com.demo.mi_cafeteria.model.entity.UsuarioPWD;
import com.demo.mi_cafeteria.model.requests.RegistryRequest;
import com.demo.mi_cafeteria.model.responses.RegistryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateNewUserService {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserPwdService userPwdService;

    @Autowired
    private RolService rolService;

    public RegistryResponse crearNuevoUsuario(RegistryRequest request){
        UsuarioInfo userInfo=userInfoService.crearInfoUsuario(request);

        if (userInfo == null) {
            return new RegistryResponse(null, "ocurrio un error, creando su registro, revise sus parametros");
        }
        UsuarioPWD userPwd=userPwdService.crearUsuarioPwd(request, userInfo);
        if (userPwd == null) {
            return new RegistryResponse(null, "ocurrio un error, creando su contraseña, revise sus parametros");
        }
        List<Role> roles=rolService.getRolById(request.getListRolId());
        if (roles.isEmpty()) {
            return new RegistryResponse(null, "ocurrio un error, buscando el rol seleccionado, revise sus parametros");
        }
        UsuarioDto userDto=new UsuarioDto(userInfo);
        List<RolDto> roleDtoList=RolDto.toListRolDto(roles);
        userDto.setRoles(roleDtoList);
        return new RegistryResponse(userDto,"success");
    }
}
