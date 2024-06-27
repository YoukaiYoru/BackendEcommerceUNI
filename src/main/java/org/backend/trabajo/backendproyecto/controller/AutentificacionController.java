package org.backend.trabajo.backendproyecto.controller;


import jakarta.validation.Valid;
import org.backend.trabajo.backendproyecto.dto.AdminDTO.DatosAutentificaciónDTO;
import org.backend.trabajo.backendproyecto.dto.AdminDTO.DatosJWTToken;
import org.backend.trabajo.backendproyecto.model.Admin;
import org.backend.trabajo.backendproyecto.service.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutentificacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> autenticarAdmin(@RequestBody DatosAutentificaciónDTO datos) {
        try {
            Authentication authToken = new UsernamePasswordAuthenticationToken(datos.usuario(), datos.clave());
            Authentication usuarioAutenticado = authenticationManager.authenticate(authToken);
            Admin adminAutenticado = (Admin) usuarioAutenticado.getPrincipal();
            String JWTtoken = tokenService.generarToken(adminAutenticado);
            return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
