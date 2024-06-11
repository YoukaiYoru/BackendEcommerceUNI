package org.backend.trabajo.backendproyecto.service;


import org.backend.trabajo.backendproyecto.dto.DetallesDTO;
import org.backend.trabajo.backendproyecto.dto.OrdenAndDetailDTO;
import org.backend.trabajo.backendproyecto.dto.TodasLasOrdenesDTO;
import org.backend.trabajo.backendproyecto.dto.OrdenDTO;
import org.backend.trabajo.backendproyecto.model.Orden;
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

    public List<TodasLasOrdenesDTO> obtenerOrdenesDetallasDeClientes() {
        List<Object[]> results = ordenRepository.findDetailedOrdenInfo();

        return results.stream()
                .map(result -> {
                    Long clientId = (Long) result[0];
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
                .collect(Collectors.toMap(
                        TodasLasOrdenesDTO::idClient,
                        userOrders -> userOrders,
                        (existing, replacement) -> {
                            existing.Ordenes().addAll(replacement.Ordenes());
                            return existing;
                        }
                ))
                .values().stream().collect(Collectors.toList());
    }






}
