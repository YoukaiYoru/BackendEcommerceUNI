package org.backend.trabajo.backendproyecto.service;

import org.backend.trabajo.backendproyecto.RuntimeExceptionCustom.ClienteAlreadyExistsException;
import org.backend.trabajo.backendproyecto.dto.ClienteDTO.ClienteDTO;
import org.backend.trabajo.backendproyecto.dto.ClienteDTO.DatosRegistroClienteDTO;
import org.backend.trabajo.backendproyecto.model.Cliente;
import org.backend.trabajo.backendproyecto.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<ClienteDTO> obtenerPorID(Long idClient) {
        return convierteDatos(clienteRepository.findByIdClient(idClient));
    }

    public void guardarUsuario(DatosRegistroClienteDTO datosRegistroClienteDTO) {
        String clientEmail = datosRegistroClienteDTO.getClientEmail();
        String clientUser = datosRegistroClienteDTO.getClientUser();
        List<Cliente> clienteExistente= clienteRepository.findByClientUserOrClientEmail(clientUser, clientEmail);
         if(!clienteExistente.isEmpty()){
             throw new ClienteAlreadyExistsException("El cliente con el email " + clientEmail +
                     " y nombre de usuario " + clientUser + " ya está registrado.");
         }else{
             clienteRepository.save(new Cliente(datosRegistroClienteDTO));
         }

    }


    @Transactional
    public void eliminarClientePorLogin(String login) {
       List<Cliente> cliente = clienteRepository.findByClientUser(login);
       clienteRepository.delete(cliente.getFirst());
    }

    //ADITIONAL SERVICE


//Mejorar este método
    @Transactional
    public void actualizarPassword(String login, String oldPassword, String newPassword) {
        List<Cliente> cliente = clienteRepository.findByClientUser(login);
        if (cliente.isEmpty()) {
            throw new RuntimeException("No existe el cliente con el login " + login);
        }

        Cliente clienteActualizado = cliente.get(0); // Obtener el primer (y se supone único) cliente de la lista
        if (clienteActualizado.getClientPassword().equals(oldPassword)) {
            clienteRepository.updatePassword(newPassword, login);
        } else {
            throw new RuntimeException("Contraseña incorrecta de " + login);
        }
    }


    public boolean verificacionDeUsuario(String login, String password){
        List<ClienteDTO> cliente = convierteDatos(clienteRepository.findByClientUser(login));
        if(cliente.isEmpty()){
            throw new RuntimeException("No existe el cliente con el login " + login);
        }else if(password.equals(cliente.getFirst().clientPassword())){
            return true;
        }else{
            throw new RuntimeException("Contraseña incorrecta");
        }
    }

}
