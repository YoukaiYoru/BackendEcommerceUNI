package org.backend.trabajo.backendproyecto.service;

import org.backend.trabajo.backendproyecto.model.Cliente;
import org.backend.trabajo.backendproyecto.model.Orden;
import org.backend.trabajo.backendproyecto.model.OrdenDetalles;
import org.backend.trabajo.backendproyecto.repository.ClienteRepository;
import org.backend.trabajo.backendproyecto.repository.OrdenDetalleRepository;
import org.backend.trabajo.backendproyecto.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrdenDetalleService {

    @Autowired
    OrdenDetalleRepository ordenDetalleRepository;
    @Autowired
    OrdenRepository ordenRepository;
    @Autowired
    ClienteRepository clienteRepository;

    public void eliminarProductoDeOrdenDetalles(Long userId, Long orderId, Long productoId) {
        // Buscar al cliente por su ID
        Cliente cliente = clienteRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Obtener la orden del cliente por su ID
        Orden orden = cliente.getListOrden().stream()
                .filter(o -> o.getIdOrden().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Orden no encontrada para el usuario dado"));

        // Buscar el detalle de orden por el ID del producto
        OrdenDetalles ordenDetalles = orden.getOrdenDetalles().stream()
                .filter(detalle -> detalle.getProducto().getIdProducto().equals(productoId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Detalle de orden no encontrado"));

        // Calcular el nuevo monto de la orden restando el precio del producto eliminado
        float nuevoOrdenMonto = orden.getOrdenMonto() - (ordenDetalles.getProductoPrecio() * ordenDetalles.getCantidadProducto());
        orden.setOrdenMonto(nuevoOrdenMonto);

        // Eliminar el detalle de orden de la lista de detalles de la orden
        orden.getOrdenDetalles().remove(ordenDetalles);

        // Guardar los cambios en la base de datos
        ordenRepository.save(orden);
    }
}
