package org.backend.trabajo.backendproyecto.dto;

import java.util.List;

public record TodasLasOrdenesDTO(
        Long idClient,
        List<OrdenAndDetailDTO> Ordenes) {
}
