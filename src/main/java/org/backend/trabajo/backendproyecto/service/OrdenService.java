package org.backend.trabajo.backendproyecto.service;

import jakarta.persistence.EntityNotFoundException;
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
    public Orden crearOrdenInicial (Long idCliente){
        // Buscar al cliente
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + idCliente));

        // Crear la orden
        Orden orden = new Orden();
        orden.setCliente(cliente);
        orden.setMetodoPago(null);
        orden.setOrdenEstado(null);
        orden.setOrdenDate(null);
        orden.setDateDelivery(null);
        orden.setOrdenMonto(0);

        return ordenRepository.save(orden);
    }

    @Transactional
    public Orden finalizarOrden(Long idOrden, int idMetodoPago){

        // Buscar la orden por ID
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada con ID: " + idOrden));

        // Buscar el método de pago
        MetodoPago metodoPago = metodoPagoRepository.findById(idMetodoPago)
                .orElseThrow(() -> new EntityNotFoundException("Método de pago no encontrado con ID: " + idMetodoPago));

        // Obtener los detalles de la orden
        List<OrdenDetalles> ordenDetalles = ordenDetalleRepository.findByOrden(orden);

        // Obtener el valor de pedido
        OrdenEstado estadoFinalizada = ordenEstadoRepository.findByOrdenEstadoNombre("pedido")
                .orElseThrow(() -> new EntityNotFoundException("Estado de orden 'pedido' no encontrado"));

        // Calcular el monto total
        float montoTotal = 0;
        for (OrdenDetalles detalle : ordenDetalles) {
            detalle.setOrden(orden);

            montoTotal += detalle.getSubTotalPrecio();
        }

        orden.setOrdenMonto(montoTotal);
        orden.setOrdenDate(LocalDate.now());
        orden.setMetodoPago(metodoPago);
        orden.setOrdenEstado(estadoFinalizada); //
        return ordenRepository.save(orden);
    }

    @Transactional
    public Orden editarEstadoOrden(Long idOrden, String ordenEstado) {
        // Buscar la orden por ID
        Orden orden = ordenRepository.findById(idOrden)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada con ID: " + idOrden));

        // Buscar el ordenEstado por Name
        OrdenEstado estadoOrden = ordenEstadoRepository.findByOrdenEstadoNombre(ordenEstado)
                .orElseThrow(() -> new EntityNotFoundException("OrdenEstado no encontrada con el nombre: " + ordenEstado));

        orden.setOrdenEstado(estadoOrden);
        orden.setDateDelivery(LocalDate.now());

        return ordenRepository.save(orden);
    }

}






