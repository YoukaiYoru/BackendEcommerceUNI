package org.backend.trabajo.backendproyecto.service;

import org.backend.trabajo.backendproyecto.dto.ClienteDTO;
import org.backend.trabajo.backendproyecto.dto.DatosRegistroClienteDTO;
import org.backend.trabajo.backendproyecto.model.Cliente;
import org.backend.trabajo.backendproyecto.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> convierteDatos(List<Cliente> clienteList){
        return clienteList.stream()
                .map(u -> new ClienteDTO(u.getIdClient(),u.getClientUser(),u.getClientPassword(),u.getClientFirstName(),u.getClientLastName(),u.getClientEmail(),u.getClientPhone()))
                .collect(Collectors.toList());
    }

    public List<ClienteDTO> obtenerTodosClientes() {
        return convierteDatos(clienteRepository.findAll());
    }

    public List<ClienteDTO> obtenerPorLogin(String login) {
        return convierteDatos(clienteRepository.findByClientUser(login));
    }

    public void guardarUsuario(DatosRegistroClienteDTO datosRegistroClienteDTO) {
        clienteRepository.save(new Cliente(datosRegistroClienteDTO));
    }


}
