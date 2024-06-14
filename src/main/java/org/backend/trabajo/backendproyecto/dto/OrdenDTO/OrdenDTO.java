package org.backend.trabajo.backendproyecto.dto.OrdenDTO;

import org.backend.trabajo.backendproyecto.model.OrdenDetalles;

import java.time.LocalDate;
import java.util.List;

public record OrdenDTO(
        Long idOrder,
        float orderAmount,
        LocalDate orderDate,
        LocalDate dateDelivery,
        Long idClient,
        List<OrdenDetalles> ordenDetallesList,
        String idOrderStatus) {
}
