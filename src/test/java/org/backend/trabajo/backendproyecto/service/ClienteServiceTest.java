package org.backend.trabajo.backendproyecto.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.backend.trabajo.backendproyecto.RuntimeExceptionCustom.ClienteAlreadyExistsException;
import org.backend.trabajo.backendproyecto.dto.ClienteDTO.ClienteDTO;
import org.backend.trabajo.backendproyecto.dto.ClienteDTO.DatosRegistroClienteDTO;
import org.backend.trabajo.backendproyecto.model.Cliente;
import org.backend.trabajo.backendproyecto.repository.ClienteRepository;
import org.backend.trabajo.backendproyecto.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerTodosClientesTest() {
        Cliente cliente = new Cliente();
        cliente.setIdClient(1L);
        cliente.setClientUser("user1");

        when(clienteRepository.findAll()).thenReturn(Collections.singletonList(cliente));

        List<ClienteDTO> clientes = clienteService.obtenerTodosClientes();
        assertEquals(1, clientes.size());
        assertEquals("user1", clientes.getFirst().clientUser());
    }

    @Test
    void obtenerPorLoginTest() {
        Cliente cliente = new Cliente();
        cliente.setIdClient(1L);
        cliente.setClientUser("user1");

        when(clienteRepository.findByClientUser("user1")).thenReturn(Collections.singletonList(cliente));

        List<ClienteDTO> clientes = clienteService.obtenerPorLogin("user1");
        assertEquals(1, clientes.size());
        assertEquals("user1", clientes.getFirst().clientUser());
    }

    @Test
    void obtenerPorIDTest() {
        Cliente cliente = new Cliente();
        cliente.setIdClient(1L);
        cliente.setClientUser("user1");

        when(clienteRepository.findByIdClient(1L)).thenReturn(Collections.singletonList(cliente));

        List<ClienteDTO> clientes = clienteService.obtenerPorID(1L);
        assertEquals(1, clientes.size());
        assertEquals("user1", clientes.getFirst().clientUser());
    }

    @Test
    void guardarUsuarioTest() {
        DatosRegistroClienteDTO datos = new DatosRegistroClienteDTO(
                "test@example.com",
                "user1",
                "password",
                "FirstName",
                "LastName",
                "123456789"
        );

        when(clienteRepository.findByClientUserAndClientEmail("user1", "test@example.com")).thenReturn(Collections.emptyList());

        clienteService.guardarUsuario(datos);

        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void guardarUsuarioYaExisteTest() {
        DatosRegistroClienteDTO datos = new DatosRegistroClienteDTO(
                "test@example.com",
                "user1",
                "password",
                "FirstName",
                "LastName",
                "123456789"
        );

        Cliente clienteExistente = new Cliente();
        clienteExistente.setClientEmail("test@example.com");
        clienteExistente.setClientUser("user1");

        when(clienteRepository.findByClientUserAndClientEmail("user1", "test@example.com")).thenReturn(Collections.singletonList(clienteExistente));

        assertThrows(ClienteAlreadyExistsException.class, () -> clienteService.guardarUsuario(datos));
    }

    @Test
    void eliminarClientePorLoginTest() {
        Cliente cliente = new Cliente();
        cliente.setClientUser("user1");

        when(clienteRepository.findByClientUser("user1")).thenReturn(Collections.singletonList(cliente));

        clienteService.eliminarClientePorLogin("user1");

        verify(clienteRepository, times(1)).delete(cliente);
    }

    @Test
    void actualizarPasswordTest() {
        Cliente cliente = new Cliente();
        cliente.setClientUser("user1");
        cliente.setClientPassword("oldPassword");

        when(clienteRepository.findByClientUser("user1")).thenReturn(Collections.singletonList(cliente));

        clienteService.actualizarPassword("user1", "oldPassword", "newPassword");

        verify(clienteRepository, times(1)).updatePassword("newPassword", "user1");
    }

    @Test
    void actualizarPasswordIncorrectaTest() {
        Cliente cliente = new Cliente();
        cliente.setClientUser("user1");
        cliente.setClientPassword("wrongPassword");

        when(clienteRepository.findByClientUser("user1")).thenReturn(Collections.singletonList(cliente));

        assertThrows(RuntimeException.class, () -> clienteService.actualizarPassword("user1", "oldPassword", "newPassword"));
    }
}