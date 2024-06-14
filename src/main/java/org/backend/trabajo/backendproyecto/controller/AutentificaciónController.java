package org.backend.trabajo.backendproyecto.controller;


import jakarta.validation.Valid;
import org.backend.trabajo.backendproyecto.dto.AdminDTO.DatosAutentificaciónDTO;
import org.backend.trabajo.backendproyecto.dto.AdminDTO.DatosJWTToken;
import org.backend.trabajo.backendproyecto.model.Cliente;
import org.backend.trabajo.backendproyecto.service.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutentificaciónController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autentificaciónAdmin(@Valid @RequestBody DatosAutentificaciónDTO datosAdmin){
        Authentication authToken =  new UsernamePasswordAuthenticationToken(datosAdmin.usuario(),
                datosAdmin.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Cliente) usuarioAutenticado);
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}
