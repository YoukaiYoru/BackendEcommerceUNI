package org.backend.trabajo.backendproyecto.controller;

import org.backend.trabajo.backendproyecto.dto.ClienteDTO;
import org.backend.trabajo.backendproyecto.dto.DatosRegistroClienteDTO;
import org.backend.trabajo.backendproyecto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ClienteDTO obtenerPorId(@PathVariable Long id){
        return ClienteService.obtenerPorID(id);
    }

    @PostMapping("/register")
    public void registrarCliente(@RequestBody DatosRegistroClienteDTO datosCliente) {
        ClienteService.guardarUsuario(datosCliente);
    }



    @DeleteMapping("/{login}")
    public void eliminarClientePorLogin(@PathVariable String login) {
        ClienteService.eliminarClientePorLogin(login);
    }

}