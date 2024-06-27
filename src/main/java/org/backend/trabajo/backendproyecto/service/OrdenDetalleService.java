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
        //Crear nuevo dto para no crear loop por esos datos - producto - orden

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