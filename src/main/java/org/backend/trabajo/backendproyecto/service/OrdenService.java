package org.backend.trabajo.backendproyecto.service;


import org.backend.trabajo.backendproyecto.dto.OrdenDTO;
import org.backend.trabajo.backendproyecto.model.Orden;
import org.backend.trabajo.backendproyecto.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenService {
    @Autowired
    private OrdenRepository ordenRepository;

    public List<OrdenDTO> obtenerTodasLasOrdenes() {
        return convierteDatos(ordenRepository.findAll());
    }


    public List<OrdenDTO> convierteDatos(List<Orden> orden){
        return orden.stream()
                .map(o -> new OrdenDTO(o.getId(),o.getOrden_details(),o.getOrden_dia(),o.getUsuario(),o.getOrden_estado()))
                .collect(Collectors.toList());
    }


    public OrdenDTO obtenerPorId(Long id) {
        Optional<Orden> orden = ordenRepository.findById(id);
        if (orden.isPresent()){
            Orden o = orden.get();
            return new OrdenDTO(o.getId(),o.getOrden_details(),o.getOrden_dia(),o.getUsuario(),o.getOrden_estado());
        }
        return null;
    }
}
