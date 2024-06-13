package org.backend.trabajo.backendproyecto.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.backend.trabajo.backendproyecto.model.Cliente;
import org.backend.trabajo.backendproyecto.model.Orden;
import org.backend.trabajo.backendproyecto.model.OrdenDetalles;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.backend.trabajo.backendproyecto.repository.ClienteRepository;
import org.backend.trabajo.backendproyecto.repository.OrdenDetalleRepository;
import org.backend.trabajo.backendproyecto.repository.OrdenRepository;
import org.backend.trabajo.backendproyecto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OrdenDetalleService {

    @Autowired
    OrdenDetalleRepository ordenDetalleRepository;
    @Autowired
    OrdenRepository ordenRepository;
    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Transactional
    public void eliminarProductoDeOrdenDetalles(Long userId, Long orderId, Long productoId) {

        Cliente cliente = clienteRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + userId));

        // Validar si la orden existe para el cliente
        Orden orden = cliente.getListOrden().stream()
                .filter(o -> o.getIdOrden().equals(orderId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada para el usuario con ID: " + userId));

        // Validar si el detalle de orden existe para la orden
        OrdenDetalles ordenDetalles = orden.getOrdenDetalles().stream()
                .filter(detalle -> !Objects.equals(detalle.getProducto().getIdProducto(), productoId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Detalle de orden no encontrado para la orden con ID: " + orderId));

        // Calcular el nuevo monto de la orden restando el precio del producto eliminado
        float nuevoOrdenMonto = orden.getOrdenMonto() - (ordenDetalles.getProductoPrecio() * ordenDetalles.getCantidadProducto());
        orden.setOrdenMonto(nuevoOrdenMonto);

        // Eliminar el detalle de orden de la lista de detalles de la orden
        orden.getOrdenDetalles().remove(ordenDetalles);

        // Guardar los cambios en la base de datos
        ordenRepository.save(orden);
    }


    @Transactional
    public OrdenDetalles agregarProductoAOrdenDetalles(String usr, Long id_producto, int cantidad) {
        List<Cliente> cliente = clienteRepository.findByClientUser(usr);
        Optional<Producto> producto = productoRepository.findById(id_producto);
        OrdenDetalles ordenDetalles = new OrdenDetalles();
        Producto p = producto.orElseGet(producto::orElseThrow);
        ordenDetalles.setProducto(p);
        ordenDetalles.setCantidadProducto(cantidad);
        ordenDetalles.setProductoPrecio(p.getProductPrice());
        ordenDetalles.setSubTotalPrecio(p);

        return ordenDetalleRepository.save(ordenDetalles);

    }
}
