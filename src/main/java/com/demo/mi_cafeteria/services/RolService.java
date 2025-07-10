package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.Roles;
import com.demo.mi_cafeteria.model.requests.CreateRoleRequest;
import com.demo.mi_cafeteria.model.responses.CreateRoleResponse;
import com.demo.mi_cafeteria.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RoleRepository roleRepository;

    public CreateRoleResponse createNewRole(CreateRoleRequest request){
        CreateRoleResponse response=new CreateRoleResponse();
        if (request.getNombre().isEmpty() || request.getNombre().isBlank()
        && request.getDescripcion().isEmpty() ||request.getDescripcion().isBlank()) {
            response.setMensaje("Ha ocurrido un error, datos del rol no suficientes");
            return response;
        }
        Roles rol=new Roles();
        rol.setDescripcionRol(request.getDescripcion());
        rol.setNombreRol(rol.getNombreRol());

        Roles savedRol=roleRepository.save(rol);
        response.setRol(savedRol);
        response.setMensaje("success");
        return response;

    }

    public List<Roles>getAllRoles(){
        return roleRepository.findAll();
    }

}
