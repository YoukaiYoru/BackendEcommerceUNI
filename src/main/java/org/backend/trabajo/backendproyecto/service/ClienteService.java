package org.backend.trabajo.backendproyecto.service;

import org.backend.trabajo.backendproyecto.dto.ClienteDTO;
import org.backend.trabajo.backendproyecto.dto.DatosRegistroClienteDTO;
import org.backend.trabajo.backendproyecto.model.Cliente;
import org.backend.trabajo.backendproyecto.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    //CONVERTIR DATOS A JSON

    public List<ClienteDTO> convierteDatos(List<Cliente> clienteList){
        return clienteList.stream()
                .map(u -> new ClienteDTO(u.getIdClient(),u.getClientUser(),u.getClientPassword(),u.getClientFirstName(),u.getClientLastName(),u.getClientEmail(),u.getClientPhone()))
                .collect(Collectors.toList());
    }

    //METODOS CRUD

    public List<ClienteDTO> obtenerTodosClientes() {
        return convierteDatos(clienteRepository.findAll());
    }

    public List<ClienteDTO> obtenerPorLogin(String login) {
        return convierteDatos(clienteRepository.findByClientUser(login));
    }

    public List<ClienteDTO> obtenerPorID(Long idClient) {
        return convierteDatos(clienteRepository.findByIdClient(idClient));
    }

    public void guardarUsuario(DatosRegistroClienteDTO datosRegistroClienteDTO) {
        clienteRepository.save(new Cliente(datosRegistroClienteDTO));
    }

    public void actualizarClientePorUsuarioYContraseña(String login, String password, DatosRegistroClienteDTO datosRegistroClienteDTO) {
        List<Cliente> clienteList = clienteRepository.findByClientUserAndClientPassword(login, password);

        if (!clienteList.isEmpty()) {
            Cliente cliente = clienteList.get(0); // Tomamos el primer cliente de la lista

            cliente.setClientUser(datosRegistroClienteDTO.getClientUser());
            cliente.setClientPassword(datosRegistroClienteDTO.getClientPassword());
            cliente.setClientFirstName(datosRegistroClienteDTO.getClientFirstName());
            cliente.setClientLastName(datosRegistroClienteDTO.getClientLastName());
            cliente.setClientEmail(datosRegistroClienteDTO.getClientEmail());
            cliente.setClientPhone(datosRegistroClienteDTO.getClientPhone());

            clienteRepository.save(cliente);
        } else {
            throw new IllegalArgumentException("No se encontró ningún cliente con el usuario y contraseña especificados");
        }
    }


    public void eliminarClientePorLogin(String login) {
        clienteRepository.findByClientUser(login);
    }

    //ADITIONAL SERVICE



}
