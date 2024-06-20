package org.backend.trabajo.backendproyecto.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.backend.trabajo.backendproyecto.RuntimeExceptionCustom.*;
import org.backend.trabajo.backendproyecto.dto.OrdenDTO.DetallesDTO;
import org.backend.trabajo.backendproyecto.dto.OrdenDTO.OrdenDTO;
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
    @Autowired
    private ClienteService clienteService;



    @Transactional
    public OrdenDetalles agregarProductoAOrdenDetalles(String usr, Long id_producto, int cantidad) {
        List<Cliente> cliente = clienteRepository.findByClientUser(usr);
        Optional<Producto> producto = productoRepository.findById(id_producto);
        OrdenDetalles ordenDetalles = new OrdenDetalles();
        Producto p = producto.orElseGet(producto::orElseThrow);
        ordenDetalles.setProducto(p);
        ordenDetalles.setCantidadProducto(cantidad);
        ordenDetalles.setSubTotalPrecio(p.getProductPrice()*cantidad);

        return ordenDetalleRepository.save(ordenDetalles);

    }

    @Transactional
    public Orden eliminarProductoDeOrdenDetalles(DetallesDTO ordenDTO, Long idOrden, String login, String password) throws UnauthorizedException {
        // Verificar las credenciales del usuario
        if (!clienteService.verificacionDeUsuario(login, password)) {
            throw new UnauthorizedException("Credenciales inválidas");
        }

        // Obtener la orden por su ID
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new OrdenNotFoundException("Orden no encontrada"));

        // Obtener la lista de detalles de la orden
        List<OrdenDetalles> ordenDetallesList = orden.getOrdenDetalles();

        if (ordenDetallesList.isEmpty()) {
            throw new OrdenTimeException("No hay detalles en la orden");
        }

        // Buscar el detalle del producto en la lista de detalles de la orden
        OrdenDetalles ordenDetalleEncontrado = null;

        for (OrdenDetalles ordenDetalle : ordenDetallesList) {
            if (ordenDetalle.getProducto().getIdProducto().equals(ordenDTO.idProducto())) {
                ordenDetalle.setCantidadProducto(ordenDetalle.getCantidadProducto() - 1);
                orden.setOrdenMonto(orden.getOrdenMonto() - ordenDetalle.getProducto().getProductPrice());

                // Si la cantidad del producto es 0, eliminar el detalle de la lista
                if (ordenDetalle.getCantidadProducto() == 0) {
                    ordenDetalleEncontrado = ordenDetalle;
                }

                break;
            }
        }

        if (ordenDetalleEncontrado != null) {
            ordenDetallesList.remove(ordenDetalleEncontrado);
            ordenDetalleRepository.delete(ordenDetalleEncontrado);
        } else {
            throw new DetalleNotFoundException("Producto no encontrado");
        }

        return ordenRepository.save(orden);
    }
}
