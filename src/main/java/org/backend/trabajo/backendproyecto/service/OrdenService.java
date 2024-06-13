package org.backend.trabajo.backendproyecto.service;


import org.backend.trabajo.backendproyecto.dto.OdenDTO.DetallesDTO;
import org.backend.trabajo.backendproyecto.dto.OdenDTO.OrdenAndDetailDTO;
import org.backend.trabajo.backendproyecto.dto.OdenDTO.TodasLasOrdenesDTO;
import org.backend.trabajo.backendproyecto.dto.OdenDTO.OrdenDTO;
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





}
