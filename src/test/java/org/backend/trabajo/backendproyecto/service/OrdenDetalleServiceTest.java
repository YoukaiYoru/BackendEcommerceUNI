package org.backend.trabajo.backendproyecto.service;

import jakarta.persistence.EntityNotFoundException;
import org.backend.trabajo.backendproyecto.model.Cliente;
import org.backend.trabajo.backendproyecto.model.Orden;
import org.backend.trabajo.backendproyecto.model.OrdenDetalles;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.backend.trabajo.backendproyecto.repository.ClienteRepository;
import org.backend.trabajo.backendproyecto.repository.OrdenDetalleRepository;
import org.backend.trabajo.backendproyecto.repository.OrdenRepository;
import org.backend.trabajo.backendproyecto.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class OrdenDetalleServiceTest {


    @Mock
    private OrdenDetalleRepository ordenDetalleRepository;

    @Mock
    private OrdenRepository ordenRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private OrdenDetalleService ordenDetalleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void eliminarProductoDeOrdenDetallesTest() {
        Cliente cliente = new Cliente();
        cliente.setIdClient(1L);
        Orden orden = new Orden();
        orden.setIdOrden(1L);
        orden.setOrdenMonto(100.0f);

        Producto producto = new Producto();
        producto.setIdProducto(1L);
        producto.setProductPrice(25.0f);

        OrdenDetalles ordenDetalles = new OrdenDetalles();
        ordenDetalles.setProducto(producto);
        ordenDetalles.setCantidadProducto(2);
        ordenDetalles.setProductoPrecio(25.0f);
        ordenDetalles.setSubTotalPrecio(producto);

        orden.setOrdenDetalles(Collections.singletonList(ordenDetalles));
        cliente.setListOrden(Collections.singletonList(orden));

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(ordenRepository.save(any(Orden.class))).thenReturn(orden);

        ordenDetalleService.eliminarProductoDeOrdenDetalles(1L, 1L, 1L);

        verify(ordenRepository, times(1)).save(any(Orden.class));
        assertEquals(50.0f, orden.getOrdenMonto());
        assertTrue(orden.getOrdenDetalles().isEmpty());
    }

    @Test
    void agregarProductoAOrdenDetallesTest() {
        Cliente cliente = new Cliente();
        cliente.setClientUser("user1");

        Producto producto = new Producto();
        producto.setIdProducto(1L);
        producto.setProductPrice(25.0f);

        when(clienteRepository.findByClientUser("user1")).thenReturn(Collections.singletonList(cliente));
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(ordenDetalleRepository.save(any(OrdenDetalles.class))).thenReturn(new OrdenDetalles());

        OrdenDetalles result = ordenDetalleService.agregarProductoAOrdenDetalles("user1", 1L, 2);

        verify(ordenDetalleRepository, times(1)).save(any(OrdenDetalles.class));
        assertNotNull(result);
        assertEquals(producto, result.getProducto());
        assertEquals(2, result.getCantidadProducto());
        assertEquals(25.0f, result.getProductoPrecio());
        assertEquals(50.0f, result.getSubTotalPrecio());
    }

    @Test
    void eliminarProductoDeOrdenDetalles_ClienteNoEncontradoTest() {
        when(clienteRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> ordenDetalleService.eliminarProductoDeOrdenDetalles(1L, 1L, 1L)
        );

        assertTrue(thrown.getMessage().contains("Cliente no encontrado con ID: 1"));
    }

    @Test
    void eliminarProductoDeOrdenDetalles_OrdenNoEncontradaTest() {
        Cliente cliente = new Cliente();
        cliente.setIdClient(1L);
        cliente.setListOrden(Collections.emptyList());

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> ordenDetalleService.eliminarProductoDeOrdenDetalles(1L, 1L, 1L)
        );

        assertTrue(thrown.getMessage().contains("Orden no encontrada para el usuario con ID: 1"));
    }

    @Test
    void eliminarProductoDeOrdenDetalles_DetalleNoEncontradoTest() {
        Cliente cliente = new Cliente();
        cliente.setIdClient(1L);
        Orden orden = new Orden();
        orden.setIdOrden(1L);
        orden.setOrdenDetalles(Collections.emptyList());

        cliente.setListOrden(Collections.singletonList(orden));

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        EntityNotFoundException thrown = assertThrows(
                EntityNotFoundException.class,
                () -> ordenDetalleService.eliminarProductoDeOrdenDetalles(1L, 1L, 1L)
        );

        assertTrue(thrown.getMessage().contains("Detalle de orden no encontrado para la orden con ID: 1"));
    }

    @Test
    void agregarProductoAOrdenDetalles_ProductoNoEncontradoTest() {
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> ordenDetalleService.agregarProductoAOrdenDetalles("user1", 1L, 2)
        );

        assertTrue(thrown.getMessage().contains("Producto no encontrado con ID: 1"));
    }
}