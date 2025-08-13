package com.demo.mi_cafeteria.services;

import com.demo.mi_cafeteria.model.dto.RolDto;
import com.demo.mi_cafeteria.model.entity.Role;
import com.demo.mi_cafeteria.model.requests.CreateRoleRequest;
import com.demo.mi_cafeteria.model.responses.CreateRoleResponse;
import com.demo.mi_cafeteria.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Role rol=new Role();
        rol.setDescripcionRol(request.getDescripcion());
        rol.setNombreRol(rol.getNombreRol());

        Role savedRol=roleRepository.save(rol);
        response.setRol(RolDto.toRolDto(savedRol));
        response.setMensaje("success");
        return response;
    }

    public List<RolDto>getAllRoles(){
        List<Role>roleList=roleRepository.findAll();
        return RolDto.toListRolDto(roleList);
    }

    public List<Role> getRolById(List<Integer> rolesIdList){

        List<Role>roleList=new ArrayList<>();
        for (Integer idRol:rolesIdList){
            roleList.add(roleRepository.getReferenceById(idRol));
        }
        return roleList;
    }

}
