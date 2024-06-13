package org.backend.trabajo.backendproyecto.controller;


import jakarta.validation.Valid;
import org.backend.trabajo.backendproyecto.dto.AdminDTO.DatosAutentificaciónDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AutentificaciónController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity autentificaciónAdmin(@Valid @RequestBody DatosAutentificaciónDTO datosAdmin){
        Authentication token =  new UsernamePasswordAuthenticationToken(datosAdmin.usuario(),datosAdmin.clave());
        authenticationManager.authenticate(token);
        return ResponseEntity.ok().body(datosAdmin);
    }

}
