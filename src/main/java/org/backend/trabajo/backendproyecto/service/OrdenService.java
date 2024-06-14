package org.backend.trabajo.backendproyecto.service;


import jakarta.transaction.Transactional;
import org.backend.trabajo.backendproyecto.dto.OrdenDTO.DetallesDTO;
import org.backend.trabajo.backendproyecto.dto.OrdenDTO.OrdenAndDetailDTO;
import org.backend.trabajo.backendproyecto.dto.OrdenDTO.TodasLasOrdenesDTO;
import org.backend.trabajo.backendproyecto.dto.OrdenDTO.OrdenDTO;
import org.backend.trabajo.backendproyecto.model.*;
import org.backend.trabajo.backendproyecto.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository ordenRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private OrdenDetalleRepository ordenDetalleRepository;
    @Autowired
    private MetodoPagoRepository metodoPagoRepository;
    @Autowired
    private OrdenEstadoRepository ordenEstadoRepository;


    public List<OrdenDTO> convierteDatos(List<Orden> ordenList){
        return ordenList.stream()
                .map(o -> new OrdenDTO(o.getIdOrden(),o.getOrdenMonto(),o.getOrdenDate(),o.getDateDelivery(),o.getCliente().getIdClient(),o.getOrdenDetalles(),o.getOrdenEstado().getOrdenEstadoNombre()))
                .collect(Collectors.toList());
    }

    public List<OrdenDTO> obtenerTodasLasOrdenes() {
        return convierteDatos(ordenRepository.findAll());
    }

    public List<TodasLasOrdenesDTO> convierteDatosDeOrdenes(List<Orden> ordenList) {
        // Agrupar las órdenes por idClient
        Map<Long, List<Orden>> groupedByClient = ordenList.stream()
                .collect(Collectors.groupingBy(o -> o.getCliente().getIdClient()));

        // Convertir el Map en una lista de TodasLasOrdenesDTO
        return groupedByClient.entrySet().stream()
                .map(entry -> {
                    Cliente cliente = entry.getValue().getFirst().getCliente();
                    List<OrdenAndDetailDTO> ordenes = entry.getValue().stream()
                            .map(o -> new OrdenAndDetailDTO(
                                    o.getIdOrden(),
                                    o.getOrdenMonto(),
                                    o.getOrdenEstado().getOrdenEstadoNombre(),
                                    o.getMetodoPago().getMetodo_pago(),
                                    o.getOrdenDate(),
                                    o.getDateDelivery(),
                                    o.getOrdenDetalles().stream()
                                            .map(d -> new DetallesDTO(
                                                    d.getProducto().getIdProducto(),
                                                    d.getProducto().getProductName(),
                                                    d.getCantidadProducto(),
                                                    d.getSubTotalPrecio()
                                            )).collect(Collectors.toList())
                            )).collect(Collectors.toList());

                    return new TodasLasOrdenesDTO(
                            cliente.getIdClient(),
                            cliente.getClientUser(),
                            ordenes
                    );
                }).collect(Collectors.toList());
    }

    public List<TodasLasOrdenesDTO> obtenerUsuariosYOrdenes(){
        return convierteDatosDeOrdenes(ordenRepository.findAll());
    }

    public OrdenDTO obtenerPorId(Long id_order) {
        Optional<Orden> orden = ordenRepository.findById(id_order);
        if (orden.isPresent()){
            Orden o = orden.get();
            return new OrdenDTO(o.getIdOrden(),o.getOrdenMonto(),o.getOrdenDate(),o.getDateDelivery(),o.getCliente().getIdClient(),o.getOrdenDetalles(),o.getOrdenEstado().getOrdenEstadoNombre());
        }
        return null;
    }

    public List<OrdenDTO> obtenerPorClientUsr(String clientUsr) {
        return convierteDatos(ordenRepository.findByClientUser(clientUsr));
    }

    @Transactional
    public Orden crearOrden(Long idCliente, List<OrdenDetalles> detallesOrden, int idMetodoPago, int idOrdenEstado) {
        // Buscar al cliente
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Buscar el método de pago
        MetodoPago metodoPago = metodoPagoRepository.findById(idMetodoPago)
                .orElseThrow(() -> new RuntimeException("Método de pago no encontrado"));

        // Buscar el estado de la orden
        OrdenEstado ordenEstado = ordenEstadoRepository.findById(idOrdenEstado)
                .orElseThrow(() -> new RuntimeException("Estado de orden no encontrado"));

        // Crear la orden
        Orden orden = new Orden();
        orden.setCliente(cliente);
        orden.setMetodoPago(metodoPago);
        orden.setOrdenEstado(ordenEstado);
        orden.setOrdenDate(LocalDate.now());
        orden.setDateDelivery(LocalDate.now().plusDays(5)); // Ejemplo de fecha de entrega
        orden.setOrdenDetalles(detallesOrden);

        // Calcular el monto total de la orden
        float montoTotal = (float) detallesOrden.stream().mapToDouble(OrdenDetalles::getSubTotalPrecio).sum();
        orden.setOrdenMonto(montoTotal);

        for (OrdenDetalles detalle : detallesOrden) {
            detalle.setOrden(orden);
            Producto producto = productoRepository.findById(detalle.getProducto().getIdProducto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            // Verificar y restar la cantidad del producto del stock
            if (producto.getProductStock() < detalle.getCantidadProducto()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getProductName());
            }

            producto.setProductStock(producto.getProductStock() - detalle.getCantidadProducto());
            productoRepository.save(producto);
        }

        return ordenRepository.save(orden);
    }


}






