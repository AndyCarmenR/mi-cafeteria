package com.demo.mi_cafeteria.resources;

import com.demo.mi_cafeteria.model.requests.AuthRequest;
import com.demo.mi_cafeteria.model.responses.AuthResponse;
import com.demo.mi_cafeteria.model.responses.RegistryResponse;
import com.demo.mi_cafeteria.services.CreateNewUserService;
import com.demo.mi_cafeteria.services.UserPwdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.demo.mi_cafeteria.utils.Constants.DIRECCION_RAIZ;

@RestController
@RequestMapping(DIRECCION_RAIZ+"auth")
public class AuthResource {
    @Autowired
    private CreateNewUserService createNewUserService;

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
    public ResponseEntity<RegistryResponse> registro(@RequestBody AuthResponse.RegistryRequest request){
        RegistryResponse response = createNewUserService.crearNuevoUsuario(request);
        if (response.getMensaje().equals("success")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
