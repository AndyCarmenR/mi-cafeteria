package com.demo.mi_cafeteria.resources;

import com.demo.mi_cafeteria.model.AuthRequest;
import com.demo.mi_cafeteria.model.AuthResponse;
import com.demo.mi_cafeteria.model.RegistryRequest;
import com.demo.mi_cafeteria.model.RegistryResponse;
import com.demo.mi_cafeteria.services.CreateNewUser;
import com.demo.mi_cafeteria.services.JwtService;
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

@RestController
@RequestMapping("/mi-cafeteria/api")
public class AuthResource {
   /* @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;*/

    @Autowired
    private CreateNewUser createNewUserService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        /*Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = jwtService.generarToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));*/
        return null;
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
