package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.requests.RegistryRequest;
import com.demo.mi_cafeteria.model.responses.AuthResponse;
import com.demo.mi_cafeteria.model.entity.UsuarioInfo;
import com.demo.mi_cafeteria.repository.UsuarioInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.demo.mi_cafeteria.utils.NullOrWhiteSpaceUtils.isBlankOrWhiteSpace;
import static com.demo.mi_cafeteria.utils.NullOrWhiteSpaceUtils.isNull;

@Service
public class UserInfoService {


    @Autowired
    private UsuarioInfoRepository userInfoRepository;
    private static final Logger log = LoggerFactory.getLogger(UserInfoService.class);

    public UsuarioInfo crearInfoUsuario(RegistryRequest request){
        if (isNull(request.getNombreUsuario()) || isBlankOrWhiteSpace(request.getNombreUsuario())
        || isNull(request.getApPaternoUsuario()) || isBlankOrWhiteSpace(request.getApPaternoUsuario())
        || isNull(request.getEmailUsuario()) || isBlankOrWhiteSpace(request.getEmailUsuario())){
            log.info("parametros insuficientes para crear un nuevo usuario");
            return null;
        }

        UsuarioInfo usuarioInfo=new UsuarioInfo();
        usuarioInfo.setNombreUsuario(request.getNombreUsuario());
        usuarioInfo.setApellidoPaternoUsuario(request.getApPaternoUsuario());
        usuarioInfo.setEmail(request.getEmailUsuario());
        UsuarioInfo saved=new UsuarioInfo();
        try {
            log.info("antes de crear nuevo registro de informacion de usuario");
            saved = userInfoRepository.save(usuarioInfo);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return saved;
    }
}
