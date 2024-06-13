package org.backend.trabajo.backendproyecto.dto.OdenDTO;

import java.util.List;

public record TodasLasOrdenesDTO(
        Long idClient,
        String clienteUser,
        List<OrdenAndDetailDTO> Ordenes) {
}
