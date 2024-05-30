package org.backend.trabajo.backendproyecto.dto;

import org.backend.trabajo.backendproyecto.model.Usuario;

import java.time.LocalDate;

public record OrdenDTO(
        Long id,
        String orden_details,
        LocalDate orden_dia,
        Usuario usuario,
        org.backend.trabajo.backendproyecto.model.RefFacturaEstado orden_estado) {
}
