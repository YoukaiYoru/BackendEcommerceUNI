package org.backend.trabajo.backendproyecto.dto.OrdenDTO;

import org.backend.trabajo.backendproyecto.dto.OrdenDTO.DetallesDTO;

import java.time.LocalDate;
import java.util.List;

public record OrdenDTO(
        Long idOrder,
        float orderAmount,
        LocalDate orderDate,
        LocalDate dateDelivery,
        Long idClient,
        String idOrderStatus,
        List<DetallesDTO> ordenDetallesList
        ) {
}
