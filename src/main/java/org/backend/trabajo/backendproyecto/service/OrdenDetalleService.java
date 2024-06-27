package org.backend.trabajo.backendproyecto.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.backend.trabajo.backendproyecto.model.Orden;
import org.backend.trabajo.backendproyecto.model.OrdenDetalles;
import org.backend.trabajo.backendproyecto.model.Producto;
import org.backend.trabajo.backendproyecto.repository.OrdenDetalleRepository;
import org.backend.trabajo.backendproyecto.repository.OrdenRepository;
import org.backend.trabajo.backendproyecto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenDetalleService {

    @Autowired
    private OrdenDetalleRepository ordenDetalleRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private ProductoRepository productoRepository;

//   @Transactional
//    public void eliminarProductoDeOrdenDetalles(Long userId, Long orderId, Long productoId) {
//
//        Cliente cliente = clienteRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + userId));
//
//        // Validar si la orden existe para el cliente
//        Orden orden = cliente.getListOrden().stream()
//                .filter(o -> o.getIdOrden().equals(orderId))
//                .findFirst()
//                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada para el usuario con ID: " + userId));
//
//        // Validar si el detalle de orden existe para la orden
//        OrdenDetalles ordenDetalles = orden.getOrdenDetalles().stream()
//                .filter(detalle -> Objects.equals(detalle.getProducto().getIdProducto(), productoId))
//                .findFirst()
//                .orElseThrow(() -> new EntityNotFoundException("Detalle de orden no encontrado para la orden con ID: " + orderId));
//
//
//
//        // Calcular el nuevo monto de la orden restando el precio del producto eliminado
//        float nuevoOrdenMonto = orden.getOrdenMonto() - (ordenDetalles.getProducto().getProductPrice() * ordenDetalles.getCantidadProducto());
//        orden.setOrdenMonto(nuevoOrdenMonto);
//        OrdenDetalles detallesEliminar = (OrdenDetalles) ordenDetalleRepository.findByOrden_IdOrdenAndProducto_IdProductoAndOrden_Cliente_IdClient(orderId, productoId, userId);
//        ordenDetalleRepository.delete(detallesEliminar);
//        // Guardar los cambios en la base de datos
//        ordenRepository.save(orden);
//    }

/*
    @Transactional
    public OrdenDetalles agregarProductoAOrdenDetalles(Long id_producto, Long id_orden, int cantidad) {
        // Buscar orden por ID
        Orden orden = ordenRepository.findById(id_orden)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada con ID: " + id_orden));

        // Buscar producto por ID
        Producto producto = productoRepository.findById(id_producto)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + id_producto));

        // Verificar y restar la cantidad del producto del stock
        if (producto.getProductStock() < cantidad) {
            throw new IllegalStateException("Stock insuficiente para el producto: " + producto.getProductName());
        }
        producto.setProductStock(producto.getProductStock() - cantidad);
        productoRepository.save(producto);

        // Crear y asignar valores a OrdenDetalles
        OrdenDetalles ordenDetalles = new OrdenDetalles();
        ordenDetalles.setProducto(producto);
        ordenDetalles.setCantidadProducto(cantidad);
        ordenDetalles.setSubTotalPrecio(producto.getProductPrice() * cantidad);
        ordenDetalles.setOrden(orden);

        // Guardar OrdenDetalles
        OrdenDetalles savedDetalle = ordenDetalleRepository.save(ordenDetalles);

        // Actualizar el monto total de la orden
        float montoTotal = (float) orden.getOrdenDetalles().stream()
                .mapToDouble(OrdenDetalles::getSubTotalPrecio)
                .sum() + ordenDetalles.getSubTotalPrecio();
        orden.setOrdenMonto(montoTotal);

        // Guardar la orden con el monto actualizado
        ordenRepository.save(orden);

        return savedDetalle;
    }
*/

    /*
        @Transactional
        public Orden eliminarProductoDeOrdenDetalles(DetallesDTO ordenDTO, Long idOrden, String login, String password) {
            if (!clienteService.verificacionDeUsuario(login, password)) {
                throw new EntityNotFoundException("Credenciales de cliente no válidas.");
            }

            List<Cliente> cliente = clienteRepository.findByClientUser(login);

            Orden orden = ordenRepository.findById(idOrden)
                    .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada con ID: " + idOrden));

            List<OrdenDetalles> ordenDetallesList = orden.getOrdenDetalles();
            if (ordenDetallesList.isEmpty()) {
                throw new OrdenTimeException("No hay detalles en la orden con ID: " + idOrden);
            }

            boolean productoEncontrado = false;

            for (OrdenDetalles ordenDetalle : ordenDetallesList) {
                if (ordenDetalle.getProducto().getIdProducto().equals(ordenDTO.idProducto())) {
                    int nuevaCantidad = ordenDetalle.getCantidadProducto() - 1;
                    ordenDetalle.setCantidadProducto(nuevaCantidad);
                    orden.setOrdenMonto(orden.getOrdenMonto() - ordenDetalle.getProducto().getProductPrice());

                    if (nuevaCantidad == 0) {
                        ordenDetallesList.remove(ordenDetalle);
                    }
                    productoEncontrado = true;
                    break;
                }
            }

            if (!productoEncontrado) {
                throw new DetalleNotFound("Producto no encontrado en la orden con ID: " + idOrden);
            }

            if (ordenDetallesList.isEmpty()) {
                return ordenRepository.save(orden);
            }

            return ordenRepository.save(orden);
        }
    */
    @Transactional
    public OrdenDetalles agregarProductosAOrdenDetalles(Long idProducto, Long idOrden, int cantidadPedidaProducto) {
        // Buscar orden por ID
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada con ID: " + idOrden));

        // Buscar producto por ID
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + idProducto));

        // Verificar el stock
        if (producto.getProductStock() < cantidadPedidaProducto) {
            throw new IllegalStateException("Stock insuficiente para el producto: " + producto.getProductName());
        }

        // Actualizar stock
        producto.setProductStock(producto.getProductStock() - cantidadPedidaProducto);
        productoRepository.save(producto);

        // Crear y asignar valores a OrdenDetalles
        OrdenDetalles ordenDetalles = new OrdenDetalles();
        ordenDetalles.setCantidadProducto(cantidadPedidaProducto);
        ordenDetalles.setSubTotalPrecio(producto.getProductPrice() * cantidadPedidaProducto);
        ordenDetalles.setProducto(producto);
        ordenDetalles.setOrden(orden);

        return ordenDetalleRepository.save(ordenDetalles);
    }

    @Transactional
    public OrdenDetalles modificarCantidadDeProductosAOrdenDetalles(Long idOrden, Long idProducto, int nuevaCantidadPedidaProducto) {
        // Buscar orden por ID
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada con ID: " + idOrden));

        // Buscar producto por ID
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con ID: " + idProducto));

        // Buscar el detalle de la orden
        OrdenDetalles ordenDetalles = ordenDetalleRepository.findByOrdenAndProducto(orden, producto)
                .orElseThrow(() -> new EntityNotFoundException("Detalle de orden no encontrado para la orden y producto especificados"));

        // Verificar el stock
        if (producto.getProductStock() + ordenDetalles.getCantidadProducto() < nuevaCantidadPedidaProducto) {
            throw new IllegalStateException("Stock insuficiente para el producto: " + producto.getProductName());
        }

        // Si la nueva cantidad pedida es 0, eliminar el detalle de la orden
        if (nuevaCantidadPedidaProducto == 0) {
            producto.setProductStock(producto.getProductStock() + ordenDetalles.getCantidadProducto());
            productoRepository.save(producto);
            ordenDetalleRepository.delete(ordenDetalles);
            return null;
        }

        // Actualizar stock
        producto.setProductStock(producto.getProductStock() + ordenDetalles.getCantidadProducto() - nuevaCantidadPedidaProducto);
        productoRepository.save(producto);

        ordenDetalles.setCantidadProducto(nuevaCantidadPedidaProducto);
        ordenDetalles.setSubTotalPrecio(producto.getProductPrice() * nuevaCantidadPedidaProducto);

        return ordenDetalleRepository.save(ordenDetalles);
    }
}