package org.backend.trabajo.backendproyecto.controller;


import jakarta.validation.Valid;
import org.backend.trabajo.backendproyecto.dto.AdminDTO.DatosAutentificaciónDTO;
import org.backend.trabajo.backendproyecto.dto.AdminDTO.DatosJWTToken;
import org.backend.trabajo.backendproyecto.model.Admin;
import org.backend.trabajo.backendproyecto.service.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutentificacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autentificacionAdmin(@Valid @RequestBody DatosAutentificaciónDTO datos){
        Authentication authToken =  new UsernamePasswordAuthenticationToken(datos.usuario(),
                datos.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Admin) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}
