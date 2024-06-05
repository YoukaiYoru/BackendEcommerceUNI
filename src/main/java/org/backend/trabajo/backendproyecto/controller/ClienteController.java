package org.backend.trabajo.backendproyecto.controller;

import org.backend.trabajo.backendproyecto.dto.ClienteDTO;
import org.backend.trabajo.backendproyecto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ClienteController {
    @Autowired
    private ClienteService ClienteService;

    @GetMapping()
    public List<ClienteDTO> obtenerUsuarios() {
        return ClienteService.obtenerTodosClientes();
    }


    @GetMapping("/{login}")
    public List<ClienteDTO> obtenerUsrPorLogin(@PathVariable String login) {
        return ClienteService.obtenerPorLogin(login);
    }

}