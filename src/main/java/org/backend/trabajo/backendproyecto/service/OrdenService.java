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


    public List<OrdenDTO> convierteDatos(List<Orden> ordenList){
        return ordenList.stream()
                .map(o -> new OrdenDTO(o.getId_order(),o.getOrder_amount(),o.getOrder_date(),o.getDate_delivery(),o.getCliente(),o.getOrdenEstado(),o.getMetodoPago()))
                .collect(Collectors.toList());
    }


    public OrdenDTO obtenerPorId(Long id_order) {
        Optional<Orden> orden = ordenRepository.findById(id_order);
        if (orden.isPresent()){
            Orden o = orden.get();
            return new OrdenDTO(o.getId_order(),o.getOrder_amount(),o.getOrder_date(),o.getDate_delivery(),o.getCliente(),o.getOrdenEstado(),o.getMetodoPago());
        }
        return null;
    }

    public List<OrdenDTO> obtenerPorClientId(Long client_id) {
        return convierteDatos(ordenRepository.findByIdUsr(client_id));
    }

}
