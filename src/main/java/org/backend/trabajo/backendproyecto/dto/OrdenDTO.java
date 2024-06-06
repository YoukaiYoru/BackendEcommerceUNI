package org.backend.trabajo.backendproyecto.dto;

import org.backend.trabajo.backendproyecto.model.OrdenEstado;
import org.backend.trabajo.backendproyecto.model.Cliente;

import java.time.LocalDate;

public record OrdenDTO(
        Long idOrder,
        float orderAmount,
        LocalDate orderDate,
        LocalDate dateDelivery,
        Cliente idClient,
        OrdenEstado idOrderStatus) {
}
