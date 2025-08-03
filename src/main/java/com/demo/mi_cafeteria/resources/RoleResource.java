package com.demo.mi_cafeteria.resources;

import com.demo.mi_cafeteria.model.entity.Roles;
import com.demo.mi_cafeteria.model.requests.CreateRoleRequest;
import com.demo.mi_cafeteria.model.responses.CreateRoleResponse;
import com.demo.mi_cafeteria.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.demo.mi_cafeteria.utils.Constants.DIRECCION_RAIZ;

@RestController
@RequestMapping(DIRECCION_RAIZ+"role")
public class RoleResource {

    @Autowired
    private RolService rolService;

    @PostMapping("/nuevo")
    public ResponseEntity<CreateRoleResponse> createNewRole(@RequestBody CreateRoleRequest request ){
        CreateRoleResponse response=rolService.createNewRole(request);
        if (response.getRol() == null) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Roles>>getallRoles(){
        return new ResponseEntity<>(rolService.getAllRoles(),HttpStatus.OK);
    }
}
