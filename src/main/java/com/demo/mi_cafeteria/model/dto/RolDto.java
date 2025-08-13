package com.demo.mi_cafeteria.model.dto;

import com.demo.mi_cafeteria.model.entity.Role;

import java.util.ArrayList;
import java.util.List;

public class RolDto {
    private Integer rolId;
    private String nombreRol;
    private String descripcionRol;

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public static RolDto toRolDto(Role rol) {
        RolDto rolDto = new RolDto();
        rolDto.setDescripcionRol(rol.getDescripcionRol());
        rolDto.setNombreRol(rol.getNombreRol());
        rolDto.setRolId(rol.getRolId());
        return rolDto;
    }

    public static List<RolDto> toListRolDto(List<Role> roleList){
         List<RolDto> rolDtoList=new ArrayList<>();
        for (Role rol:roleList){
            RolDto rolDto=toRolDto(rol);
            rolDtoList.add(rolDto);
        }
        return rolDtoList;
    }
}