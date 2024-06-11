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
    private ClienteService clienteService;

    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> obtenerUsuarios() {
        List<ClienteDTO> clientes = clienteService.obtenerTodosClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<List<ClienteDTO>> obtenerUsrPorLogin(@PathVariable String login) {
        List<ClienteDTO> cliente = clienteService.obtenerPorLogin(login);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerPorId(@PathVariable Long id){
        ClienteDTO clientbyId = clienteService.obtenerPorID(id);
        return ResponseEntity.ok(clientbyId);
    }

    @PostMapping("/register")
    public ResponseEntity<DatosRespuestaClienteDTO> registrarCliente(@RequestBody @Valid DatosRegistroClienteDTO datosCliente,
                                           UriComponentsBuilder uriComponentsBuilder) {
            clienteService.guardarUsuario(datosCliente);
            DatosRespuestaClienteDTO r = new DatosRespuestaClienteDTO(datosCliente.getClientUser(), datosCliente.getClientPassword(),
                    datosCliente.getClientFirstName(), datosCliente.getClientLastName(), datosCliente.getClientEmail(), datosCliente.getClientPhone());
            URI url = uriComponentsBuilder.path("/user/login/{login}").buildAndExpand(datosCliente.getClientUser()).toUri();
            return ResponseEntity.created(url).body(r);
    }

    @Transactional
    @DeleteMapping("/delete/{login}")
    public ResponseEntity eliminarClientePorLogin(@PathVariable String login) {
        boolean eliminado = clienteService.eliminarClientePorLogin(login);
        if (eliminado) {
            return ResponseEntity.noContent().build(); // El cliente fue eliminado con éxito
        } else {
            return ResponseEntity.notFound().build(); // No se encontró el cliente
        }
    }

}