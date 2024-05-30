package org.backend.trabajo.backendproyecto.controller;

import org.backend.trabajo.backendproyecto.dto.UsuarioDTO;
import org.backend.trabajo.backendproyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioService UsuarioServicio;

    @GetMapping()
    public List<UsuarioDTO> obtenerUsuarios() {
        return UsuarioServicio.obtenerTodosUsuarios();
    }


    @GetMapping("/{login}")
    public List<UsuarioDTO> obtenerUsrPorLogin(@PathVariable String login) {
        return UsuarioServicio.obtenerPorLogin(login);
    }

}