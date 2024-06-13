package org.backend.trabajo.backendproyecto.dto.OdenDTO;

import java.time.LocalDate;

public record OrdenDTO(
        Long idOrder,
        float orderAmount,
        LocalDate orderDate,
        LocalDate dateDelivery,
        Long idClient,
        String idOrderStatus) {
}
