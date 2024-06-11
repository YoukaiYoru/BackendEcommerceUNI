package org.backend.trabajo.backendproyecto.service;


import org.backend.trabajo.backendproyecto.dto.DetallesDTO;
import org.backend.trabajo.backendproyecto.dto.OrdenAndDetailDTO;
import org.backend.trabajo.backendproyecto.dto.TodasLasOrdenesDTO;
import org.backend.trabajo.backendproyecto.dto.OrdenDTO;
import org.backend.trabajo.backendproyecto.model.Orden;
import org.backend.trabajo.backendproyecto.model.OrdenDetalles;
import org.backend.trabajo.backendproyecto.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository ordenRepository;


    public List<OrdenDTO> convierteDatos(List<Orden> ordenList){
        return ordenList.stream()
                .map(o -> new OrdenDTO(o.getIdOrden(),o.getOrdenMonto(),o.getOrdenDate(),o.getDateDelivery(),o.getCliente().getIdClient(),o.getOrdenEstado().getOrdenEstadoNombre()))
                .collect(Collectors.toList());
    }

    public List<OrdenDTO> obtenerTodasLasOrdenes() {
        return convierteDatos(ordenRepository.findAll());
    }

    public OrdenDTO obtenerPorId(Long id_order) {
        Optional<Orden> orden = ordenRepository.findById(id_order);
        if (orden.isPresent()){
            Orden o = orden.get();
            return new OrdenDTO(o.getIdOrden(),o.getOrdenMonto(),o.getOrdenDate(),o.getDateDelivery(),o.getCliente().getIdClient(),o.getOrdenEstado().getOrdenEstadoNombre());
        }
        return null;
    }

    public List<OrdenDTO> obtenerPorClientUsr(String clientUsr) {
        return convierteDatos(ordenRepository.findByClientUser(clientUsr));
    }



//    No actualiza los valores

    public List<TodasLasOrdenesDTO> obtenerOrdenesDetallasDeClientes() {
        List<Object[]> results = ordenRepository.findDetailedOrdenInfo();

        // Mapear los resultados a DTOs de órdenes con detalles
        List<TodasLasOrdenesDTO> todasLasOrdenesDTOList = results.stream()
                .map(result -> {
                    Long clientId = (Long) result[0];
                    String clienteUser = (String) result[5];
                    Long orderId = (Long) result[6];
                    String ordenEstado = (String) result[11];
                    Float ordenPrecio = (Float) result[9];
                    String metodoPago = (String) result[10];
                    LocalDate fechaOrden = (LocalDate) result[8];
                    LocalDate fechaRecibido = (LocalDate) result[7];
                    Long productId = (Long) result[12];
                    String productName = (String) result[14];
                    Integer cantidadDeProducto = (Integer) result[16];
                    Float precioTotal = (Float) result[15];

                    return new TodasLasOrdenesDTO(
                            clientId,
                            clienteUser,
                            new ArrayList<>(List.of(new OrdenAndDetailDTO(
                                    orderId,
                                    ordenPrecio,
                                    ordenEstado,
                                    metodoPago,
                                    fechaOrden,
                                    fechaRecibido,
                                    new ArrayList<>(List.of(new DetallesDTO(
                                            productId,
                                            productName,
                                            cantidadDeProducto,
                                            precioTotal
                                    )))
                            )))
                    );
                })
                .collect(Collectors.toList());

        todasLasOrdenesDTOList.forEach(dto -> {
            Orden orden = ordenRepository.findById(dto.idClient()).orElse(null);
            if (orden != null) {
                List<OrdenDetalles> detalles = orden.getOrdenDetalles();
                List<DetallesDTO> detallesDTOs = dto.Ordenes().get(0).detallesDTOList();
                detallesDTOs.clear(); // Limpiar la lista actual de detalles DTO

                detalles.forEach(detalle -> {
                    DetallesDTO detallesDTO = new DetallesDTO(
                            detalle.getProducto().getIdProducto(),
                            detalle.getProducto().getProductName(),
                            detalle.getCantidadProducto(),
                            (float) (detalle.getProductoPrecio() * detalle.getCantidadProducto())
                    );
                    detallesDTOs.add(detallesDTO); // Agregar el nuevo DTO de detalle
                });
            }
        });

        return todasLasOrdenesDTOList;
    }





}
