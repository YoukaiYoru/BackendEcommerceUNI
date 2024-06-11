package org.backend.trabajo.backendproyecto.service;

import org.backend.trabajo.backendproyecto.RuntimeExceptionCustom.ClienteAlreadyExistsException;
import org.backend.trabajo.backendproyecto.dto.ClienteDTO;
import org.backend.trabajo.backendproyecto.dto.DatosRegistroClienteDTO;
import org.backend.trabajo.backendproyecto.model.Cliente;
import org.backend.trabajo.backendproyecto.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    public ClienteDTO convierteDatos(Cliente c){
        return new ClienteDTO(c.getIdClient(),c.getClientUser(),c.getClientPassword(),c.getClientFirstName(),c.getClientLastName(),c.getClientEmail(),c.getClientPhone());
    }
    //METODOS CRUD

    public List<ClienteDTO> obtenerTodosClientes() {
        return convierteDatos(clienteRepository.findAll());
    }

    public List<ClienteDTO> obtenerPorLogin(String login) {
        return convierteDatos(clienteRepository.findByClientUser(login));
    }

    public ClienteDTO obtenerPorID(Long idClient) {
        return convierteDatos(clienteRepository.findByIdClient(idClient));
    }

    public void guardarUsuario(DatosRegistroClienteDTO datosRegistroClienteDTO) {
        String clientEmail = datosRegistroClienteDTO.getClientEmail();
        String clientUser = datosRegistroClienteDTO.getClientUser();
        Optional<Cliente> clienteExistente= clienteRepository.findByClientUserAndClientEmail(clientUser, clientEmail);
         if(clienteExistente.isPresent()){
             throw new ClienteAlreadyExistsException("El cliente con el email " + clientEmail +
                     " y nombre de usuario " + clientUser + " ya está registrado.");
         }else{
             clienteRepository.save(new Cliente(datosRegistroClienteDTO));
         }

    }


    @Transactional
    public boolean eliminarClientePorLogin(String login) {
        List<Cliente> clientes = clienteRepository.findByClientUser(login);
        if (!clientes.isEmpty()) {
            clienteRepository.deleteAll(clientes);
            return true;
        } else {
            return false;
        }
    }
    //ADITIONAL SERVICE



}
