package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.RegistryRequest;
import com.demo.mi_cafeteria.model.UsuarioInfo;
import com.demo.mi_cafeteria.repository.UsuarioInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.mi_cafeteria.utils.NullOrWhiteSpaceUtils;

import static com.demo.mi_cafeteria.utils.NullOrWhiteSpaceUtils.isBlankOrWhiteSpace;
import static com.demo.mi_cafeteria.utils.NullOrWhiteSpaceUtils.isNull;

@Service
public class UserInfoService {

    @Autowired
    private UsuarioInfoRepository userInfoRepository;

    public UsuarioInfo crearInfoUsuario(RegistryRequest request){
        if (isNull(request.getNombreUsuario()) || isBlankOrWhiteSpace(request.getNombreUsuario())
        || isNull(request.getApPaternoUsuario()) || isBlankOrWhiteSpace(request.getApPaternoUsuario())
        || isNull(request.getEmailUsuario()) || isBlankOrWhiteSpace(request.getEmailUsuario())){
            return null;
        }
        UsuarioInfo usuarioInfo=new UsuarioInfo();
        usuarioInfo.setNombreUsuario(request.getNombreUsuario());
        usuarioInfo.setApellidoPaternoUsuario(request.getApPaternoUsuario());
        usuarioInfo.setEmail(request.getEmailUsuario());
        return userInfoRepository.save(usuarioInfo);
    }

}
