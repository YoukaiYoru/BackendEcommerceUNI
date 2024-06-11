package org.backend.trabajo.backendproyecto.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.backend.trabajo.backendproyecto.dto.ClienteDTO;
import org.backend.trabajo.backendproyecto.dto.DatosRegistroClienteDTO;
import org.backend.trabajo.backendproyecto.dto.DatosRespuestaClienteDTO;
import org.backend.trabajo.backendproyecto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/login/{login}")
    public List<ClienteDTO> obtenerUsrPorLogin(@PathVariable String login) {
        return ClienteService.obtenerPorLogin(login);
    }

    @GetMapping("/{id}")
    public ClienteDTO obtenerPorId(@PathVariable Long id){
        return ClienteService.obtenerPorID(id);
    }

    @PostMapping("/register")
    public ResponseEntity<DatosRespuestaClienteDTO> registrarCliente(@RequestBody @Valid DatosRegistroClienteDTO datosCliente,
                                           UriComponentsBuilder uriComponentsBuilder) {
        ClienteService.guardarUsuario(datosCliente);
        DatosRespuestaClienteDTO r = new DatosRespuestaClienteDTO(datosCliente.clientUser(),datosCliente.clientPassword(),
                datosCliente.clientFirstName(),datosCliente.clientLastName(),datosCliente.clientEmail(),datosCliente.clientPhone());
        URI url = uriComponentsBuilder.path("/register").build().toUri();
        return ResponseEntity.created(url).body(r);
    }





    @DeleteMapping("/{login}")
    public ResponseEntity eliminarClientePorLogin(@PathVariable String login) {
        ClienteService.eliminarClientePorLogin(login);
        return ResponseEntity.noContent().build();
    }

}