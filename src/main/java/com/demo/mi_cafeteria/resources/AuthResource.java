package com.demo.mi_cafeteria.resources;

import com.demo.mi_cafeteria.model.AuthRequest;
import com.demo.mi_cafeteria.model.AuthResponse;
import com.demo.mi_cafeteria.model.RegistryRequest;
import com.demo.mi_cafeteria.model.RegistryResponse;
import com.demo.mi_cafeteria.services.CreateNewUser;
import com.demo.mi_cafeteria.services.JwtService;
import com.demo.mi_cafeteria.services.UserPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/mi-cafeteria/api/auth")
public class AuthResource {
    @Autowired
    private CreateNewUser createNewUserService;

    @Autowired
    private UserPwdService userPwdService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        String tokenOrError = userPwdService.login(request.getNickName(), request.getPassword());
        if (tokenOrError.contains("incorrectos")) {
            return new ResponseEntity<>(new AuthResponse("",tokenOrError),HttpStatus.BAD_REQUEST);
        } else if (tokenOrError.contains("credenciales")) {
            return new ResponseEntity<>(new AuthResponse("",tokenOrError),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new AuthResponse(tokenOrError,""));
    }
    @PostMapping("/registro")
    public ResponseEntity<RegistryResponse> registro(@RequestBody RegistryRequest request){
        RegistryResponse response = createNewUserService.crearNuevoUsuario(request);
        if (response.getMensaje().equals("success")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
