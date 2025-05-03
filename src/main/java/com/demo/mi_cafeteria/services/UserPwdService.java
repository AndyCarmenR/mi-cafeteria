package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.RegistryRequest;
import com.demo.mi_cafeteria.model.UsuarioInfo;
import com.demo.mi_cafeteria.model.UsuarioPWD;
import com.demo.mi_cafeteria.repository.UsuarioPWDRepository;
import com.demo.mi_cafeteria.utils.NullOrWhiteSpaceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPwdService {

    @Autowired
    private UsuarioPWDRepository usuarioPWDRepository;

    public UsuarioPWD crearUsuarioPwd(RegistryRequest request, UsuarioInfo usuarioInfo){

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
}
