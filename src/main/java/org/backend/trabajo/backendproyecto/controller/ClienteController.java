package org.backend.trabajo.backendproyecto.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.backend.trabajo.backendproyecto.RuntimeExceptionCustom.ClienteAlreadyExistsException;
import org.backend.trabajo.backendproyecto.dto.ClienteDTO.*;
import org.backend.trabajo.backendproyecto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/get")
    public ResponseEntity<List<ClienteDTO>> obtenerUsuarios() {
        List<ClienteDTO> clientes = clienteService.obtenerTodosClientes();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/get/login/{login}")
    public ResponseEntity<List<ClienteDTO>> obtenerClientePorLogin(@PathVariable String login) {
        List<ClienteDTO> cliente = clienteService.obtenerPorLogin(login);
        if (cliente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity obtenerClientePorId(@PathVariable Long id){
        List<ClienteDTO> clientbyId = clienteService.obtenerPorID(id);
        return ResponseEntity.ok(clientbyId);
    }

    @PostMapping("/register")
    public ResponseEntity<DatosRespuestaClienteDTO> registrarCliente(@RequestBody @Valid DatosRegistroClienteDTO datosCliente,
                                           UriComponentsBuilder uriComponentsBuilder) {
        try {
            clienteService.guardarUsuario(datosCliente);
            DatosRespuestaClienteDTO r = new DatosRespuestaClienteDTO(datosCliente.getClientUser(), datosCliente.getClientPassword(),
                    datosCliente.getClientFirstName(), datosCliente.getClientLastName(), datosCliente.getClientEmail(), datosCliente.getClientPhone());
            URI url = uriComponentsBuilder.path("/user/login/{login}").buildAndExpand(datosCliente.getClientUser()).toUri();
            return ResponseEntity.created(url).body(r);
        } catch (ClienteAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @PostMapping("/delete")
    @Transactional
    public ResponseEntity eliminarClientePorLoginPost(@RequestParam String login) {
        clienteService.eliminarClientePorLogin(login);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/changePassword")
    public ResponseEntity cambiarPassword(@RequestBody @Valid ChangePasswordDTO password){
        clienteService.actualizarPassword(password.login(), password.oldPassword(), password.newPassword());
        List<ClienteDTO> clienteActualizado = clienteService.obtenerPorLogin(password.login());
        List<DatosRespuestaClienteDTO> r = clienteActualizado.stream()
                .map(c->new DatosRespuestaClienteDTO(
                        c.clientUser(),
                        c.clientPassword(),
                        c.clientFirstName(),
                        c.clientLastName(),
                        c.clientEmail(),
                        c.clientPhone()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(r);
    }

    @PostMapping("/acceder")
    public ResponseEntity<List<DatosRespuestaClienteDTO>> acceder(@RequestBody @Valid DatosAccesoDTO accesoDTO) {
        boolean verificacion = clienteService.verificacionDeUsuario(accesoDTO.username(), accesoDTO.password());
        if (verificacion){
            List<DatosRespuestaClienteDTO> r = clienteService.obtenerPorLogin(accesoDTO.username()).stream()
                    .map(c -> new DatosRespuestaClienteDTO(
                            c.clientUser(),
                            c.clientPassword(),
                            c.clientFirstName(),
                            c.clientLastName(),
                            c.clientEmail(),
                            c.clientPhone()
                    )).collect(Collectors.toList());
            return ResponseEntity.ok(r);
        }else{
            return (ResponseEntity<List<DatosRespuestaClienteDTO>>) ResponseEntity.badRequest();
        }
    }
}